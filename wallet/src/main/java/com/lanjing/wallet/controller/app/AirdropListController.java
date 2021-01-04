package com.lanjing.wallet.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.*;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.controller.BaseContrller;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@RestController
@SuppressWarnings("all")
@CrossOrigin(value = "*")
public class AirdropListController extends BaseContrller {

    @Resource(name = "AvailService")
    private AvailService availService;

    @Resource(name = "AirdropService")
    private AirdropService airdropService;

    @Resource(name = "AirdropListService")
    private AirdropListService airdropListService;

    @Resource(name = "CoinsService")
    private CoinsService coinsService;

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "WellethistoryStateService")
    private WellethistoryStateService wellethistoryStateService;

    @Autowired
    RedisDao redisDao;

    @Resource(name = "UsersService")
    UsersService usersService;

    /**
     * 理财项目，我的账户
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/getAirdropList")
    public Object airdropList(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        Integer id = param.getInteger("id");
        if (pageNum == null || pageSize == null || id == null) {
            return Result.error("Parameter error");
        }

        Integer userId = getUserId();
        String userKey = getUserKey();
        if (getUserId() == null || StringUtils.isEmpty(userKey)) {
            return Result.error("Please login again");
        }

        Wellets btc = welletService.selectByUserandcoin(userKey, ConfigConst.BTC_ID);
        Wellets yyb = welletService.selectByUserandcoin(userKey, ConfigConst.YYB_ID);

        if (btc == null || yyb == null) {
            return Result.error("Wallet is abnormal");
        }

        //冻结btc
        BigDecimal freezeBtc = airdropListService.sumFreezeBtc(userId, id);
        //冻结yyb
        BigDecimal freezeYyb = airdropListService.sumFreezeYyb(userId, id);

        //自由btc
        BigDecimal freeBtc = airdropListService.sumFreeBtc(userId, id);
        //自由yyb
        BigDecimal freeYyb = airdropListService.sumFreeYyb(userId, id);

        //总资产
        BigDecimal totalAssets = freezeBtc.add(freezeYyb).add(freeBtc).add(freeYyb);

        JSONObject result = new JSONObject();
        result.put("freezeBtc", DoubleTwoUtil.toShortString(freezeBtc));
        result.put("freezeYyb", DoubleTwoUtil.toShortString(freezeYyb));
        result.put("freeBtc", DoubleTwoUtil.toShortString(freeBtc));
        result.put("freeYyb", DoubleTwoUtil.toShortString(freeYyb));
        result.put("totalAssets", DoubleTwoUtil.toShortString(totalAssets));

        PageHelper.startPage(pageNum, pageSize);
        List<AirdropList> list = airdropListService.findByUserId(userId, id);
        PageInfo pageInfo = PageInfo.of(list);
        result.put("pageInfo", pageInfo);

        return Result.success(result);
    }

    /**
     * 参与项目页
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/addAirdropListPage")
    public Object addAirdropListPage(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("Parameter error");
        }

        Airdrop airdrop = airdropService.selectByPrimaryKey(id);

        //BTC价格
        BigDecimal price = coinsService.findPrice();

        BigDecimal btc = airdrop.getAmount().divide(price, 8, RoundingMode.HALF_UP);

        JSONObject result = new JSONObject();
        result.put("price", price);
        result.put("btc", btc.stripTrailingZeros().toPlainString());
        result.put("airdrop", airdrop);

        return Result.success(result);
    }

    /**
     * 参与项目
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/addAirdropList")
    @Transactional
    public Object addAirdropList(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        String password = param.getString("password");

        if (id == null) {
            return Result.error("Parameter error");
        }

        Integer userId = getUserId();

        if (userId == null) {
            return Result.error("Please login again");
        }


        Users user = usersService.selectByPrimaryKey(userId);

        String key = String.format("%s%s", ConfigConst.KEY_PASS, userId);

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
            return Result.error("交易密码错误");
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }

        Airdrop airdrop = airdropService.selectByPrimaryKey(id);

        if (airdrop == null) {
            return Result.error("Id does not exist");
        }

        Date date = new Date();

        AirdropList airdropList = new AirdropList();
        airdropList.setaId(airdrop.getId());
        airdropList.setUserId(userId);
        airdropList.setCycle(airdrop.getCycle());
        airdropList.setAmount(airdrop.getAmount());
        airdropList.setFreezeBtc(airdrop.getFreezeBtc());
        airdropList.setFreezeYyb(airdrop.getFreezeYyb());
        //
        if (airdrop.getFreeBtc().doubleValue() < 0) {
            return Result.error("free btc not enough.");
        }
        airdropList.setFreeBtc(airdrop.getFreeBtc());
        airdropList.setFreeYyb(airdrop.getFreeYyb());
        airdropList.setFreedBtc(airdrop.getFreedBtc());
        airdropList.setFreedYyb(airdrop.getFreedYyb());
        airdropList.setCreateTime(date);
        airdropList.setUpdateTime(date);

        //添加记录
        int result = airdropListService.invest(airdropList, getUserKey());

        CheckEx.db(result, "Please try again later");
        return Result.success();
    }

    /**
     * 理财转出
     *
     * @param param
     * @return
     */
    @Transactional
    @RequestMapping("/app/addAirdropList/transfer")
    public Object transfer(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");
        Integer aId = param.getInteger("aId");
        Double amount = param.getDouble("amount");
        String password = param.getString("password");

        if (id == null || amount == null || amount < 0) {
            return Result.error("Parameter error");
        }

        String userKey = getUserKey();
        Integer userId = getUserId();

        if (StringUtils.isEmpty(userKey)) {
            return Result.error("Please login again");
        }

        Users user = usersService.selectByPrimaryKey(userId);

        String key = String.format("%s%s", ConfigConst.KEY_PASS, userId);

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
            return Result.error("交易密码错误");
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }

        double price;
        if (id == ConfigConst.BTC_ID) {
            BigDecimal bigDecimal = airdropListService.sumFreeBtc(userId, aId);
            if (bigDecimal.compareTo(BigDecimal.valueOf(amount)) == -1) {
                return Result.error("Insufficient balance");
            }
            price = coinsService.findPrice().doubleValue();
        } else if (id == ConfigConst.YYB_ID) {
            BigDecimal bigDecimal = airdropListService.sumFreeYyb(userId, aId);
            if (bigDecimal.compareTo(BigDecimal.valueOf(amount)) == -1) {
                return Result.error("Insufficient balance");
            }
            price = 1;
        } else {
            return Result.error("Id error");
        }
        if (amount < 100) {
            return Result.error("The  aomunt cannot be less than 100");
        }
        //对应币种数额
        double num = amount / price;

        Wellets wallet = welletService.selectByUserandcoin(userKey, id);

        if (wallet == null) {
            return Result.error("Wallet is abnormal");
        }

        BigDecimal free = wallet.getReleasefinances();

        if (free.compareTo(BigDecimal.valueOf(amount)) == -1) {
            return Result.error("Insufficient balance");
        }

        Integer version = wallet.getVersion();

        //修改钱包
        int result = welletService.transfer(num, amount, userKey, id, version);

        if (result < 1) {
            throw new RuntimeException("Please try again later");
        }

        //扣除参加项目的自由余额
        List<AirdropList> airdropLists = airdropListService.findByUserId(userId, aId);

        if (id == ConfigConst.BTC_ID) {
            for (AirdropList airdropList : airdropLists) {
                //获取每个项目BTC自由余额扣除
                BigDecimal freeBtc = airdropList.getFreeBtc();
                if (freeBtc.compareTo(BigDecimal.ZERO) == 1) {
                    if (amount > 0) {
                        double minus;
                        if (freeBtc.doubleValue() - amount >= 0) {
                            minus = amount;
                        } else {
                            minus = amount - freeBtc.doubleValue();
                        }
                        amount = amount - minus;
                        result = airdropListService.minusFreeBtc(airdropList.getId(), minus);
                        if (result < 1) {
                            throw new RuntimeException("Deduction of free YYB failure");
                        }
                    } else {
                        break;
                    }
                }
            }
        } else {
            for (AirdropList airdropList : airdropLists) {
                //获取每个项目YYB自由余额扣除
                BigDecimal freeYyb = airdropList.getFreeYyb();
                if (freeYyb.compareTo(BigDecimal.ZERO) == 1) {
                    if (amount > 0) {
                        double minus;
                        if (freeYyb.doubleValue() - amount >= 0) {
                            minus = amount;
                        } else {
                            minus = amount - freeYyb.doubleValue();
                        }
                        amount = amount - minus;
                        result = airdropListService.minusFreeYyb(airdropList.getId(), minus);
                        if (result < 1) {
                            throw new RuntimeException("Deduction of free YYB failure");
                        }
                    } else {
                        break;
                    }
                }
            }

        }

        if (amount > 0) {
            throw new RuntimeException("The server is busy");
        }

        Date date = new Date();
        //添加钱包日志
        WellethistoryState state = new WellethistoryState();
        state.setWelletid(wallet.getFid());
        state.setUserid(Integer.valueOf(userKey));
        state.setChangenum(BigDecimal.valueOf(num));
        state.setType(3);
        state.setCointype(id);
        state.setRemark("用户理财,资产转出");
        state.setCreatetime(date);
        state.setUpdatetime(date);
        state.setKeyes(userKey);
        state.setState(1);
        wellethistoryStateService.insertSelective(state);

        return Result.success();
    }

    /**
     * 理财转出页
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/addAirdropList/transferPage")
    public Object transferPage(@RequestBody JSONObject param) {
        //币种id
        Integer id = param.getInteger("id");
        //项目id
        Integer aId = param.getInteger("aId");
        if (id == null || aId == null) {
            return Result.error("Parameter error");
        }

        String userKey = getUserKey();
        Integer userId = getUserId();
        if (StringUtils.isEmpty(userKey)) {
            return Result.error("Please login again");
        }

        BigDecimal free = airdropListService.sumFreeYyb(userId, aId);
        //币种价格
        double price = 1;
        if (id == ConfigConst.BTC_ID) {
            price = coinsService.findPrice().doubleValue();
            free = airdropListService.sumFreeBtc(userId, aId);
        }

        JSONObject result = new JSONObject();
        result.put("price", DoubleUtil.toShortString(price));
        result.put("free", DoubleUtil.toShortString(free));

        return Result.success(result);
    }

    /**
     * 理财详情
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/addAirdropList/detail")
    public Object detail(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");
        if (pageNum == null || pageSize == null) {
            return Result.error("Parameter error");
        }


        if (id == null) {
            return Result.error("Parameter error");
        }

        Integer userId = getUserId();
        if (userId == null) {
            return Result.error();
        }
        AirdropList airdropList = airdropListService.selectByPrimaryKey(id);

        if (airdropList == null) {
            return Result.error("Please login again");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Avail> list = availService.findByAIdAndUserIdAndType(id, userId, null);
        PageInfo<Avail> pageInfo = PageInfo.of(list);

        JSONObject result = new JSONObject();
        result.put("airdropList", airdropList);
        result.put("pageInfo", pageInfo);

        return Result.success(result);
    }
}
