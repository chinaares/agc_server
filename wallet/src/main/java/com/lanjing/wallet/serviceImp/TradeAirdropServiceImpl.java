package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.DateUtil;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.TradeAirdropService;
import com.lanjing.wallet.util.TradeAirdropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("TradeAirdropService")
@RestController
public class TradeAirdropServiceImpl implements TradeAirdropService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeAirdropMapper tradeAirdropMapper;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private IncomeMapper incomeMapper;


    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private TeamPerformanceMapper teamPerformanceMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private ParametersMapper parametersMapper;

    @Override
    public double sumActivation(String userKey) {
        return this.tradeAirdropMapper.sumActivation(userKey);
    }

    @Override
    public double sumFreed(String userKey) {
        return this.tradeAirdropMapper.sumFreed(userKey);
    }

    @Override
    public double sumConsume(String userKey) {
        return this.tradeAirdropMapper.sumConsume(userKey);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.tradeAirdropMapper.deleteByPrimaryKey(id);
    }

    public int insert(TradeAirdrop record) {
        return this.tradeAirdropMapper.insert(record);
    }

    public int insertSelective(TradeAirdrop record) {
        return this.tradeAirdropMapper.insertSelective(record);
    }

    public TradeAirdrop selectByPrimaryKey(Integer id) {
        return this.tradeAirdropMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(TradeAirdrop record) {
        return this.tradeAirdropMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TradeAirdrop record) {
        return this.tradeAirdropMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TradeAirdrop> selectByUserKey(String userKey) {
        return this.tradeAirdropMapper.selectByUserKey(userKey);
    }

    @Override
    public List<TradeAirdrop> selectAll() {
        return this.tradeAirdropMapper.selectAll();
    }

    @Override
    @Transactional
    public int dailyRelease(double release, TradeAirdrop tradeAirdrop, boolean status) {
        //获取用户信息
        Users user = usersMapper.selectByPrimaryKey(tradeAirdrop.getUserId());
        Integer userId = user.getFid();
        String userKey = user.getKeyes();
        Integer airdropId = tradeAirdrop.getId();
        Date date = new Date();

        //添加释放记录
        Income income = new Income();
        income.setInvestId(airdropId);
        income.setUserId(userId);
        income.setType(TradeAirdropUtil.getCode(tradeAirdrop.getType()));
        income.setAmount(release);
        income.setRemark(TradeAirdropUtil.getMsg(tradeAirdrop.getType()));
        income.setCreateTime(date);

        //购物赠送，添加购物锁仓分红记录
        if (tradeAirdrop.getType() == 1) {
            Promotion promotion = new Promotion();
            promotion.setCode(tradeAirdrop.getCode());
            promotion.setUserId(userId);
            promotion.setType(tradeAirdrop.getType());
            promotion.setAmount(release);
            promotion.setCreateTime(date);
            promotion.setUpdateTime(date);
            promotionMapper.insertSelective(promotion);
        }

        int result = incomeMapper.insertSelective(income);
        if (result < 1) {
            throw new RuntimeException("添加释放记录失败，id:" + airdropId);
        }

        //修改钱包
        Wellets wellets = welletsMapper.selectByUserandcoin(userKey, ConfigConst.DZCC_COIN);

        if (wellets == null) {
            throw new RuntimeException("DZCC钱包异常,userKey:" + userKey);
        }

        result = welletsMapper.updateCoinNumByVersion(release, ConfigConst.DZCC_COIN, userKey, wellets.getVersion());

        if (result < 1) {
            throw new RuntimeException("修改钱包失败,id:" + airdropId);
        }

        //修改购物空投记录及其状态
        TradeAirdrop updateTradeAirdrop = new TradeAirdrop();
        updateTradeAirdrop.setId(airdropId);
        updateTradeAirdrop.setDailyRelease(release);
        updateTradeAirdrop.setTotal(tradeAirdrop.getTotal() + release);
        updateTradeAirdrop.setLockBalance(tradeAirdrop.getLockBalance() - release);

        if (status) {
            //释放完成
            updateTradeAirdrop.setStatus(2);
        }
        result = tradeAirdropMapper.updateByPrimaryKeySelective(updateTradeAirdrop);

        if (result < 1) {
            throw new RuntimeException("修改释放信息失败,id:" + airdropId);
        }

        return result;
    }

    @RequestMapping("/zuul/insertTradeAirdrop")
    public String addtradeAirdrop(@RequestBody JSONObject param){
        Integer userId = param.getInteger("userId");
        Double coinnum = param.getDouble("coinnum");
        Double cnyprice = param.getDouble("cnyprice");
        Integer coin = param.getInteger("coin");
        TradeAirdrop tradeAirdrop = new TradeAirdrop();
        tradeAirdrop.setUserId(userId);
        tradeAirdrop.setCoinTransaction(cnyprice);
        tradeAirdrop.setTransaction(coinnum);
        tradeAirdrop.setCode(Long.valueOf(coin));
        if(coin == 5){
            tradeAirdrop.setType(1);
        }

        int i = insertSelective1(tradeAirdrop,coin,cnyprice);
        if (i > 0 ){
            return JSONObject.toJSONString(new ResultDTO(200,"ok"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1,"failure"));
    }

    @Transactional
    public int insertSelective1(TradeAirdrop record,Integer coin,Double cnyprice){
        Wellets wellet = welletsMapper.selectByUserandcoin(record.getUserId().toString(),5);
        double quotaNum = wellet.getQuotanum().doubleValue();
        Coins usdt = coinsMapper.selectByPrimaryKey(6);
        double coinprice = coinsMapper.selectByPrimaryKey(coin).getPrice()*usdt.getPrice();
        double dzccprice = coinsMapper.selectByPrimaryKey(5).getPrice()*usdt.getPrice();
        double trans = record.getCoinTransaction().doubleValue()/dzccprice;
        WellethistoryState wellethistoryState = new WellethistoryState();
        wellethistoryState.setWelletid(wellet.getFid());
        wellethistoryState.setChangenum(BigDecimal.valueOf(-record.getTransaction()));
        wellethistoryState.setBalance(wellet.getCoinnum());
        wellethistoryState.setType(6);
        wellethistoryState.setRemark("购物消费");
        Date time = new Date();
        wellethistoryState.setCreatetime(time);
        wellethistoryState.setUpdatetime(time);
        wellethistoryState.setState(1);
        wellethistoryState.setCointype(coin);
        wellethistoryState.setUserid(record.getUserId());
        wellethistoryStateMapper.insertSelective(wellethistoryState);

        record.setCreateTime(time);

        if(record.getType() == null){
            if(quotaNum >= trans*10){
                record.setType(2);
                record.setAmount(trans*Integer.valueOf(parametersMapper.selectByKey("shopProportion").getKeyvalue()).intValue());
                record.setLockBalance(trans*Integer.valueOf(parametersMapper.selectByKey("shopProportion").getKeyvalue()).intValue());
                record.setCycle(Integer.valueOf(parametersMapper.selectByKey("shop").getKeyvalue()));
                record.setPeriodsNum(record.getCycle()*7);
                wellet.setQuotanum(BigDecimal.valueOf(wellet.getQuotanum().doubleValue()-trans*10));
                int i = welletsMapper.updateByPrimaryKeySelective(wellet);
                if( i> 0 ){
                    int j =tradeAirdropMapper.insertSelective(record);
                    int t = addteamperformance(record.getUserId().toString(),record.getAmount());
                    return j+t;
                }
                return 0;
            }else {
                if(quotaNum>0){
                    record.setType(2);
                    record.setAmount(quotaNum);
                    record.setLockBalance(quotaNum);
                    record.setTransaction(quotaNum/Integer.valueOf(parametersMapper.selectByKey("shopProportion").getKeyvalue()).intValue()*dzccprice/coinprice);
                    record.setCycle(Integer.valueOf(parametersMapper.selectByKey("shop").getKeyvalue()));
                    record.setPeriodsNum(record.getCycle()*7);
                    wellet.setQuotanum(BigDecimal.valueOf(0));
                    int i = welletsMapper.updateByPrimaryKeySelective(wellet);
                    if( i> 0 ){
                        int j =tradeAirdropMapper.insertSelective(record);
                        int t = addteamperformance(record.getUserId().toString(),record.getAmount());
                    }
                }
                trans = trans - quotaNum/10;
                record.setType(1);
                record.setAmount(trans*Integer.valueOf(parametersMapper.selectByKey("goodsProportion").getKeyvalue()).intValue());
                record.setLockBalance(trans*Integer.valueOf(parametersMapper.selectByKey("goodsProportion").getKeyvalue()).intValue());
                record.setTransaction(trans*dzccprice/coinprice);
                record.setCycle(Integer.valueOf(parametersMapper.selectByKey("goods").getKeyvalue()));
                record.setPeriodsNum(record.getCycle()*7);
                int j =tradeAirdropMapper.insertSelective(record);
                int t = addteamperformance(record.getUserId().toString(),record.getAmount());
                return j+t;
            }
        }else{
            record.setType(1);
            record.setAmount(trans*Integer.valueOf(parametersMapper.selectByKey("goodsProportion").getKeyvalue()).intValue());
            record.setLockBalance(trans*Integer.valueOf(parametersMapper.selectByKey("goodsProportion").getKeyvalue()).intValue());
            record.setCycle(Integer.valueOf(parametersMapper.selectByKey("goods").getKeyvalue()));
            record.setPeriodsNum(record.getCycle()*7);
            wellet.setQuotanum(BigDecimal.valueOf(wellet.getQuotanum().doubleValue()-trans*10));
            int j =tradeAirdropMapper.insertSelective(record);
            int t = addteamperformance(record.getUserId().toString(),record.getAmount());
            return j+t;
        }
    }

    public int addteamperformance(String userkey,Double amount){
        Date time = new Date();
        Integer up1 = Integer.valueOf(recommendMapper.selectByUserKey1(userkey).getUserkey2());
        Integer up2 = Integer.valueOf(recommendMapper.selectByUserKey1(up1.toString()).getUserkey2());
        TeamPerformance teamPerformance = new TeamPerformance();
        teamPerformance.setUserId(up1);
        teamPerformance.setBonus(amount);
        teamPerformance.setCreateTime(time);
        int i =0;
        i += teamPerformanceMapper.insertSelective(teamPerformance);
        teamPerformance.setUserId(up2);
        i += teamPerformanceMapper.insertSelective(teamPerformance);
        return i;
    }

    /**
     * 后台添加激活
     * @param user
     * @param amount
     * @return
     */
    @Override
    public int addActivation(Users user, Double amount) {

        Date date=new Date();

        TradeAirdrop tradeAirdrop=new TradeAirdrop();
        tradeAirdrop.setUserId(user.getFid());
        //tradeAirdrop.setCode(UUIDUtil.nextId());
        tradeAirdrop.setTitle("后台添加激活");
        tradeAirdrop.setType(3);
        tradeAirdrop.setCycle(100);
        tradeAirdrop.setPeriodsNum(100*7);
        tradeAirdrop.setStatus(1);
        tradeAirdrop.setAmount(amount);
        tradeAirdrop.setTransaction(0d);
        tradeAirdrop.setLockBalance(amount);
        tradeAirdrop.setTotal(0d);
        tradeAirdrop.setDailyRelease(amount/(100*7.0));
        tradeAirdrop.setEndTime(DateUtil.getDate(100*7));
        tradeAirdrop.setCreateTime(date);
        tradeAirdrop.setUpdateTime(date);

        return tradeAirdropMapper.insertSelective(tradeAirdrop);
    }
}