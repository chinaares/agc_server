package com.lanjing.wallet.serviceImp;

import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.enums.AvailEnum;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.AirdropListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-13 11:44
 */
@SuppressWarnings("all")
@Service("AirdropListService")
public class AirdropListServiceImpl implements AirdropListService {

    @Resource
    private AirdropListMapper airdropListMapper;

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AvailMapper availMapper;

    @Override
    public long countByExample(AirdropListExample example) {
        return airdropListMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AirdropListExample example) {
        return airdropListMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return airdropListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AirdropList record) {
        return airdropListMapper.insert(record);
    }

    @Override
    public int insertSelective(AirdropList record) {
        return airdropListMapper.insertSelective(record);
    }

    @Override
    public List<AirdropList> selectByExample(AirdropListExample example) {
        return airdropListMapper.selectByExample(example);
    }

    @Override
    public AirdropList selectByPrimaryKey(Integer id) {
        return airdropListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(AirdropList record, AirdropListExample example) {
        return airdropListMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AirdropList record, AirdropListExample example) {
        return airdropListMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(AirdropList record) {
        return airdropListMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AirdropList record) {
        return airdropListMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AirdropList> findByUserId(Integer userId, Integer id) {
        return airdropListMapper.findByUserId(userId, id);
    }

    @Override
    @Transactional
    public int invest(AirdropList airdropList, String userKey) {
        Date date = new Date();
        //统计记录
        int result = airdropListMapper.insertSelective(airdropList);
        CheckEx.db(result, "Add record failed");

        //BTC价格
        double price = coinsMapper.findPrice().doubleValue();

        //用户钱包
        Wellets btc = welletsMapper.selectByUserandcoin(userKey, ConfigConst.BTC_ID);

        BigDecimal amount = airdropList.getAmount();
        //自由
        BigDecimal freeBtc = airdropList.getFreeBtc();
        //冻结
        BigDecimal freezeBtc = airdropList.getFreezeBtc();

        //对应BTC
        BigDecimal num = amount.divide(BigDecimal.valueOf(price), 8, RoundingMode.HALF_UP);

        CheckEx.error(btc.getCoinnum().compareTo(num) == -1, "Insufficient balance");

        Integer version = btc.getVersion();

        //修改用户BTC钱包
        result = welletsMapper.invest(num, freezeBtc, freeBtc, userKey, ConfigConst.BTC_ID, version);

        CheckEx.db(result, "Modify BTC wallet failed");

        //自由
        BigDecimal freeYyb = airdropList.getFreeYyb();
        //冻结
        BigDecimal freezeYyb = airdropList.getFreezeYyb();
        Wellets yyb = welletsMapper.selectByUserandcoin(userKey, ConfigConst.YYB_ID);
        //修改用户YYB钱包
        result = welletsMapper.invest(BigDecimal.ZERO, freezeYyb, freeYyb, userKey, ConfigConst.YYB_ID, yyb.getVersion());
        CheckEx.db(result, "Modify YYB wallet failed");

        //添加钱包日志
        WellethistoryState state = new WellethistoryState();
        state.setWelletid(btc.getFid());
        state.setUserid(airdropList.getUserId());
        state.setChangenum(num.negate());
        state.setType(4);
        state.setCointype(ConfigConst.BTC_ID);
        state.setRemark("用户理财");
        state.setCreatetime(date);
        state.setUpdatetime(date);
        state.setKeyes(userKey);
        state.setState(1);

        CheckEx.db(wellethistoryStateMapper.insertSelective(state));

        return result;
    }

    @Override
    public List<AirdropList> findByStatus(Integer status) {
        return airdropListMapper.findByStatus(status);
    }

    @Override
    @Transactional
    public void financialRelease(AirdropList airdropList, Boolean status) {
        Integer id = airdropList.getId();
        //每日释放BTC
        BigDecimal freedBtc = airdropList.getFreedBtc();
        //每日释放YYB
        BigDecimal freedYyb = airdropList.getFreedYyb();

        //用户id
        Integer userId = airdropList.getUserId();
        String userKey = userId.toString();

        //更新理财记录
        int result = airdropListMapper.updateDaysAndStatusById(status, id, freedBtc, freedYyb);
        CheckEx.db(result, "更新理财记录失败");

        Date date = new Date();

        Avail avail = new Avail();
        avail.setCreateTime(date);
        avail.setaId(id);
        avail.setUserId(userId);

        if (freedBtc.compareTo(BigDecimal.ZERO) == 1) {
            //添加理财BTC释放记录
            avail.setType(AvailEnum.BTC.getCode());
            avail.setAmount(freedBtc);
            avail.setRemark(AvailEnum.BTC.getMsg());

            result = availMapper.insertSelective(avail);
            CheckEx.db(result, "添加理财BTC释放记录失败");

            //修改BTC钱包,理财冻结
            Wellets btc = welletsMapper.selectByUserandcoin(userKey, ConfigConst.BTC_ID);
            result = welletsMapper.financialRelease(freedBtc, userKey, ConfigConst.BTC_ID, btc.getVersion());
            CheckEx.db(result, "更新BTC钱包失败");

            //修改BTC钱包,理财冻结
            Wellets btcLock = welletsMapper.selectByUserandcoin(userKey, ConfigConst.BTC_ID);
            result = welletsMapper.lockFinances(freedBtc, userKey, ConfigConst.BTC_ID, btcLock.getVersion());
            CheckEx.db(result, "更新BTC钱包失败");
        }
        if (freedYyb.compareTo(BigDecimal.ZERO) == 1) {
            //添加YYB释放记录
            avail.setType(AvailEnum.YYB.getCode());
            avail.setAmount(freedYyb);
            avail.setRemark(AvailEnum.YYB.getMsg());

            result = availMapper.insertSelective(avail);
            CheckEx.db(result, "添加理财YYB释放记录失败");

            //修改YYB钱包
            Wellets yyb = welletsMapper.selectByUserandcoin(userKey, ConfigConst.YYB_ID);
            result = welletsMapper.financialRelease(freedYyb, userKey, ConfigConst.YYB_ID, yyb.getVersion());
            CheckEx.db(result, "更新YYB钱包失败");

            //修改BTC钱包,理财冻结
            Wellets btcLock = welletsMapper.selectByUserandcoin(userKey, ConfigConst.BTC_ID);
            result = welletsMapper.lockFinances(freedYyb, userKey, ConfigConst.BTC_ID, btcLock.getVersion());
            CheckEx.db(result, "更新BTC钱包失败");
        }

        //释放完归还理财资金
        if (status) {
            //BTC人民币价格
            BigDecimal price = coinsMapper.findPrice();
            //理财金额
            BigDecimal amount = airdropList.getAmount();

            BigDecimal num = amount.divide(price, 8, RoundingMode.HALF_UP);

            //修改BTC钱包
            Wellets btc = welletsMapper.selectByUserandcoin(userKey, ConfigConst.BTC_ID);
            result = welletsMapper.updateCoinNumByVersion(num.doubleValue(), ConfigConst.BTC_ID, userKey, btc.getVersion());
            CheckEx.db(result, "更新BTC钱包失败");

            //添加钱包日志
            WellethistoryState state = new WellethistoryState();
            state.setWelletid(btc.getFid());
            state.setUserid(userId);
            state.setChangenum(num);
            state.setType(3);
            state.setCointype(ConfigConst.BTC_ID);
            state.setRemark("用户理财到期归还");
            state.setCreatetime(date);
            state.setUpdatetime(date);
            state.setKeyes(userKey);
            state.setState(1);

            result = wellethistoryStateMapper.insertSelective(state);
            CheckEx.db(result);
        }

    }

    @Override
    public BigDecimal sumFreeYyb(Integer userId, Integer id) {
        return airdropListMapper.sumFreeYyb(userId, id);
    }

    @Override
    public BigDecimal sumFreeBtc(Integer userId, Integer id) {
        return airdropListMapper.sumFreeBtc(userId, id);
    }

    @Override
    public BigDecimal sumFreezeYyb(Integer userId, Integer id) {
        return airdropListMapper.sumFreezeYyb(userId, id);
    }

    @Override
    public BigDecimal sumFreezeBtc(Integer userId, Integer id) {
        return airdropListMapper.sumFreezeBtc(userId, id);
    }

    @Override
    public int minusFreeBtc(Integer id, Double amount) {
        return airdropListMapper.minusFreeBtc(id, amount);
    }

    @Override
    public int minusFreeYyb(Integer id, Double amount) {
        return airdropListMapper.minusFreeYyb(id, amount);
    }
}

