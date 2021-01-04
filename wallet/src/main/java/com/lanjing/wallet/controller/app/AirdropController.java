package com.lanjing.wallet.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.DateUtil;
import com.jester.util.utils.DoubleUtil;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.dao.CoinAddressMapper;
import com.lanjing.wallet.enums.IncomeEnum;
import com.lanjing.wallet.enums.InvestEnum;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.model.po.InvestReward;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.util.NewsUtil;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@SuppressWarnings("all")
public class AirdropController extends BaseContrller {

    @Resource(name = "AirdropService")
    private AirdropService airdropService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "InformationService")
    private InformationService informationService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "InvestService")
    private InvestService investService;

    @Resource(name = "IncomeService")
    private IncomeService incomeService;

    @Resource(name = "CoinsService")
    private CoinsService coinsService;

    @Resource(name = "AirdropRewardService")
    private AirdropRewardService airdropRewardService;

    @Autowired
    private CoinAddressMapper coinAddressService;

    @Autowired
    RedisDao redisDao;

    /**
     * 获取banner
     *
     * @return
     */
    @RequestMapping("/open/app/getBanner")
    public Object getBanner() {
        //查询banner
        List<Parameters> banner_zh = parametersService.selectByType(1);
        List<Parameters> banner_en = parametersService.selectByType(2);
        List<Parameters> banner_jp = parametersService.selectByType(4);
        HashMap hashMap = new HashMap();
        hashMap.put("banner_zh", banner_zh);
        hashMap.put("banner_en", banner_en);
        hashMap.put("banner_jp", banner_jp);
        return Result.success(hashMap);
    }

    /**
     * 获取公告
     *
     * @return
     */
    @RequestMapping("/open/app/getInformation")
    public Object getInformation(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<Information> information_en = informationService.selectByEdition(0);
        PageInfo<Information> information_en_page = new PageInfo<>(information_en);

        PageHelper.startPage(pageNum, pageSize);
        List<Information> information_zh = informationService.selectByEdition(1);
        PageInfo<Information> information_zh_page = new PageInfo<>(information_zh);

        PageHelper.startPage(pageNum, pageSize);
        List<Information> information_jp = informationService.selectByEdition(2);
        PageInfo<Information> information_jp_page = new PageInfo<>(information_jp);

        HashMap hashMap = new HashMap();
        hashMap.put("information_zh", information_zh_page);
        hashMap.put("information_en", information_en_page);
        hashMap.put("information_jp", information_jp_page);
        return Result.success(hashMap);
    }

    /**
     * 获取资讯
     *
     * @return
     */
    @RequestMapping("/open/app/getNews")
    public Object getNews(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        PageHelper.startPage(pageNum, pageSize);

        List<Information> information_en = informationService.findByTypeAndEdition(2, 0);
        PageInfo<Information> information_en_page = new PageInfo<>(information_en);

        PageHelper.startPage(pageNum, pageSize);
        List<Information> information_zh = informationService.findByTypeAndEdition(2, 1);
        PageInfo<Information> information_zh_page = new PageInfo<>(information_zh);

        PageHelper.startPage(pageNum, pageSize);
        List<Information> information_jp = informationService.findByTypeAndEdition(2, 2);
        PageInfo<Information> information_jp_page = new PageInfo<>(information_jp);

        HashMap hashMap = new HashMap();
        hashMap.put("information_zh", information_zh_page);
        hashMap.put("information_en", information_en_page);
        hashMap.put("information_jp", information_jp_page);
        if (redisDao.hasKey(ConfigConst.GOLDEN_FINANCE)){
            String result = redisDao.getValue(ConfigConst.GOLDEN_FINANCE);
            hashMap.put("goldenFinance", JSONArray.parseArray(result));
        }else {
            JSONArray info = NewsUtil.getInfo();
            hashMap.put("goldenFinance", info);
            if (info!=null){
                redisDao.setKey(ConfigConst.GOLDEN_FINANCE,info.toJSONString());
                redisDao.setTime(ConfigConst.GOLDEN_FINANCE,60*30);
            }
        }
        return Result.success(hashMap);
    }

    /**
     * 查询理财信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getAirdrop")
    public Object getAirdrop(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        if (pageNum == null || pageSize == null) {
            return Result.error("Parameter error");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Airdrop> list = airdropService.findAll();
        PageInfo<Airdrop> pageInfo = new PageInfo<>(list);

        return Result.success(pageInfo);
    }

    /**
     * 参加理财页信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/inAirdropsPage")
    public Object inAirdropsPage(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        Airdrop airdrop = airdropService.selectByPrimaryKey(id);
        if (airdrop == null) {
            return Result.error("理财信息不存在");
        }

        Users user = usersService.selectByUserKey(getUserKey());

        //获取DZCC余额
        Wellets wellets = welletService.selectByUserandcoin(user.getKeyes(), ConfigConst.DZCC_COIN);
        if (wellets == null) {
            return Result.error("钱包异常");
        }
        BigDecimal balance = wellets.getCoinnum();

        HashMap hashMap = new HashMap();
        hashMap.put("airdrop", airdrop);
        hashMap.put("balance", DoubleUtil.toShortString(balance));

        return Result.success(hashMap);
    }

    /**
     * 参加理财
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/inAirdrops")
    public Object inAirdrops(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");//理财信息id
        Double principal = param.getDouble("principal");//理财本金
        String password = param.getString("password");//交易密码

        if (id == null || principal == 0) {
            return Result.error("参数错误");
        }

        Users user = usersService.selectByUserKey(getUserKey());

        if (user == null) {
            return Result.error("请重新登录");
        }

        String key = String.format("%s%s", ConfigConst.KEY_PASS, user.getFid());

        Boolean hasKey = redisDao.hasKey(key);

        if (hasKey) {
            Integer num = Integer.valueOf(redisDao.getValue(key));
            //每日限制交易密码只能错误三次
            if (num > 3) {
                return Result.error("交易密码错误超过3次,请明日再试");
            }
        }

        if (!password.equals(user.getTranspassword())) {
            redisDao.increment(key);
            if (!hasKey) {
                //初始化过期时间
                redisDao.setTime(key, DateUtil.nextDay() / 1000);
            }
            return Result.error("密码错误");
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }

        try {
            return airdropService.invest(user, id, principal);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    /**
     * 提前出仓
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/cancelAirdrops")
    public Object cancelAirdrops(@RequestBody JSONObject param) {
        String password = param.getString("password");
        //判断交易密码
        Users user = usersService.selectByPrimaryKey(usersService.selectByUserKey(getUserKey()).getFid());
        if (user == null) {
            return Result.error("请重新登陆");
        } else if (!user.getTranspassword().equals(password.trim())) {
            return Result.error("交易密码错误");
        }

        //获取理财记录id
        Integer id = param.getInteger("id");
        List<Invest> invests = investService.selectAllOrByPrimaryKeyOrStatusOrUserId(id, InvestEnum.INPUT.getVal(), null);

        if (invests.size() == 0) {
            return Result.error("未获取到理财记录");
        }

        Invest invest = invests.get(0);

        Integer status = invest.getStatus();

        if (status.intValue() == InvestEnum.ABORT.getVal()) {
            return Result.error("请求已提交");
        }

        if (status.intValue() == InvestEnum.PASS.getVal() || status.intValue() == InvestEnum.COMPLETE.getVal()) {
            return Result.error("当前状态不支持出仓");
        }
        return Result.error("当前状态不支持出仓");

        //取消该理财
        //try {
        //    return airdropService.cancelAirdrops(user, invest);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    return Result.error(e.getMessage());
        //}
    }


    /**
     * 用户理财记录
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getInvest")
    public Object getInvest(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Users users = usersService.selectByUserKey(getUserKey());

        //财富,获取理财总资产，理财账号余额
        Wellets e = welletService.selectByUserandcoin(users.getKeyes(), ConfigConst.ET_COIN);
        Wellets d = welletService.selectByUserandcoin(users.getKeyes(), ConfigConst.DZCC_COIN);
        if (d == null || e == null) {
            return Result.error("钱包为空");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Invest> invests = investService.selectAllOrByPrimaryKeyOrStatusOrUserId(null, null, users.getFid());
        PageInfo<Invest> pageInfo = new PageInfo<>(invests);

        HashMap hashMap = new HashMap();
        hashMap.put("eBalance", DoubleUtil.toShortString(e.getCoinnum()));
        hashMap.put("dBalance", DoubleUtil.toShortString(d.getCoinnum()));
        hashMap.put("pageInfo", pageInfo);
        return Result.success(hashMap);
    }

    /**
     * 理财记录详情
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getInvestDetails")
    public Object getInvestDetails(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer id = param.getInteger("id");

        if (pageNum == null || pageSize == null || id == null) {
            return Result.error("参数错误");
        }

        Users users = usersService.selectByUserKey(getUserKey());

        List<Invest> invests = investService.selectAllOrByPrimaryKeyOrStatusOrUserId(id, null, users.getFid());

        if (invests.size() == 0) {
            return Result.error("理财记录不存在");
        }
        Invest invest = invests.get(0);

        PageHelper.startPage(pageNum, pageSize);
        List<Income> incomes = incomeService.selectByInvestId(invest.getId(), IncomeEnum.RELEASE.getCode());
        PageInfo<Income> pageInfo = new PageInfo<>(incomes);

        HashMap hashMap = new HashMap();
        hashMap.put("invest", invest);
        hashMap.put("pageInfo", pageInfo);

        return Result.success(hashMap);
    }

    /**
     * 理财奖励
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getAirdropReward")
    public Object getAirdropReward(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Users users = usersService.selectByUserKey(getUserKey());

        PageHelper.startPage(pageNum, pageSize);
        List<InvestReward> invests = investService.selectByUserId(users.getFid());
        PageInfo<InvestReward> pageInfo = new PageInfo<>(invests);

        return Result.success(pageInfo);
    }

    /**
     * EXC奖励
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getEXCReward")
    public Object getEXCReward(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Users users = usersService.selectByUserKey(getUserKey());

        PageHelper.startPage(pageNum, pageSize);
        List<AirdropReward> airdropRewards = airdropRewardService.selectAllOrByPrimaryKeyOrStatusOrUserIdOrType(null, null, users.getFid(), null);
        PageInfo<AirdropReward> pageInfo = new PageInfo<>(airdropRewards);

        return Result.success(pageInfo);
    }

    /**
     * 获取地址
     */
    @RequestMapping("/app/getCoinAddress")
    public Object getCoinAddress(@RequestBody JSONObject param) {
        if (param.size() == 0) {
            return Result.error("参数错误");
        }

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageNum == null || pageSize == null) {
            return Result.error("参数错误");
        }

        Integer userId = getUserId();

        if (userId == null) {
            return Result.error("请重新登录");
        }

        PageHelper.startPage(pageNum, pageSize);
        CoinAddressExample coinAddressExample = new CoinAddressExample();
        CoinAddressExample.Criteria criteria = coinAddressExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<CoinAddress> coinAddresses = coinAddressService.selectByExample(coinAddressExample);

        PageInfo<CoinAddress> pageInfo = PageInfo.of(coinAddresses);
        return Result.success(pageInfo);
    }

    /**
     * 添加地址
     */
    @RequestMapping("/app/addCoinAddress")
    public Object getCoinAddress(@RequestBody CoinAddress coinAddress) {
        //获取地址
        String address = coinAddress.getAddress();
        if (StringUtils.isEmpty(address)) {
            return Result.error("参数错误");
        }

        Integer userId = getUserId();

        CoinAddress oldAddress = coinAddressService.findByUserIdAndAddress(getUserId(), address);
        if (oldAddress != null) {
            return Result.error("该地址已存在");
        }

        if (coinAddress.getStatus().intValue() == 1) {
            CoinAddressExample coinAddressExample = new CoinAddressExample();
            CoinAddressExample.Criteria criteria = coinAddressExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andStatusEqualTo(1);

            CoinAddress updateAddress = new CoinAddress();
            updateAddress.setStatus(0);

            coinAddressService.updateByExampleSelective(updateAddress, coinAddressExample);
        }

        coinAddress.setUserId(userId);
        coinAddress.setCreateTime(new Date());

        int result = coinAddressService.insertSelective(coinAddress);

        if (result < 1) {
            return Result.error("添加失败");
        }

        return Result.success();
    }

    /**
     * 修改地址
     */
    @RequestMapping("/app/updateCoinAddress")
    public Object updateCoinAddress(@RequestBody CoinAddress coinAddress) {
        //获取地址
        String address = coinAddress.getAddress();
        if (StringUtils.isEmpty(address) || coinAddress.getId() == null) {
            return Result.error("参数错误");
        }

        Integer userId = getUserId();

        Integer count = coinAddressService.countByIdAndUserId(coinAddress.getId(), userId);
        if (count < 1) {
            return Result.error("id不存在");
        }

        if (coinAddress.getStatus().intValue() == 1) {
            CoinAddressExample coinAddressExample = new CoinAddressExample();
            CoinAddressExample.Criteria criteria = coinAddressExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andStatusEqualTo(1);

            CoinAddress updateAddress = new CoinAddress();
            updateAddress.setStatus(0);

            coinAddressService.updateByExampleSelective(updateAddress, coinAddressExample);
        }

        int result = coinAddressService.updateByPrimaryKeySelective(coinAddress);

        if (result < 1) {
            return Result.error("修改失败");
        }

        return Result.success();
    }

    /**
     * 删除地址
     */
    @RequestMapping("/app/delCoinAddress")
    public Object delCoinAddress(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        if (id == null) {
            return Result.error("参数错误");
        }

        Integer count = coinAddressService.countByIdAndUserId(id, getUserId());
        if (count < 1) {
            return Result.error("id不存在");
        }

        int result = coinAddressService.deleteByPrimaryKey(id);

        if (result < 1) {
            return Result.error("删除失败");
        }
        return Result.success();
    }

}
