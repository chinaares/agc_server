package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.DateUtil;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.ex.ExException;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.service.*;
import com.lanjing.wallet.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class WelletController extends BaseContrller {

    @Resource(name = "WelletService")
    private WelletService welletService;

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Resource(name = "AddressService")
    private AddressService addressService;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Resource(name = "WellethistoryStateService")
    private WellethistoryStateService wellethistoryStateService;

    @Resource(name = "ChangelogService")
    private ChangelogService changelogService;

    @Resource(name = "OrdersService")
    private OrdersService ordersService;

    @Resource(name = "MappingTradeService")
    private MappingTradeService mappingTradeService;

    @Resource
    private CoinsMapper coinsMapper;

    @Resource
    private CoinLogMapper coinLogMapper;

    @Autowired
    private RedisDao redisDao;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Resource
    CoinAddressStoreMapper coinAddressStoreMapper;

    @Resource
    ParametersMapper parametersMapper;

    @Autowired
    private WelletsMapper welletsMapper;

    @Autowired
    private addree_userMapper addressDao;

    @Resource(name = "createCoinAddressService")
    ExecutorService executorService;

    @RequestMapping("/app/agc/coins")
    public String getCoins() {
        JSONObject json = new JSONObject();
        Coins coins = coinsMapper.selectByPrimaryKey(2);
        if (coins == null) {
            json.put("msg", "币种不存在!");
            json.put("code", -1);
            return json.toJSONString();
        }
        json.put("msg", "ok");
        json.put("code", 200);
        json.put("data", coins);
        return json.toJSONString();
    }

    @RequestMapping("/app/getwellet")
    public String getwelletlist() {
        JSONObject json = new JSONObject();
        Map<String, String> list = welletService.selectByUserKey(getUserKey());
        if (list.size() < 1) {
            json.put("msg", "请先创建钱包!");
            json.put("code", -1);
            return json.toJSONString();
        }
        json.put("msg", "ok");
        json.put("sum", list.get("sum"));
        json.put("code", 200);
        json.put("Adress", list.get("UID"));
        list.remove("sum");
        list.remove("UID");
        json.put("data", list);
        return json.toJSONString();
    }


    /**
     * 获取钱包详情
     */
    @RequestMapping("/app/getwelletdetail")
    public String getwelletDetail(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("welletfid");
        String type = map.get("type");
        String page = map.get("page");
        String size = map.get("size");
        if (fId == null || "".equals(fId)) {
            json.put("msg", "请先创建钱包！");
            json.put("code", 300);
            return json.toJSONString();
        }
        Integer type1 = null;
        Integer page1 = null;
        Integer size1 = null;
        try {
            if (type != null && !"".equals(type)) {
                type1 = Integer.parseInt(type);
            }
            if (page != null && !"".equals(page)) {
                page1 = Integer.parseInt(page);
            }
            if (size != null && !"".equals(size)) {
                size1 = Integer.parseInt(size);
            }
            Wellets wellet = welletService.selectByPrimaryKey(Integer.parseInt(fId));
            Coins coin = coinsMapper.selectByPrimaryKey(wellet.getCoinid());

            JSONObject welletjson = JSONObject.parseObject(JSON.toJSONString(wellet));
            welletjson.put("sum", BigDecimalUtils.getBigDecimalStr(wellet.getCoinnum()));
            welletjson.put("cnysum", BigDecimalUtils.multiplyStr(wellet.getCoinnum(), new BigDecimal(coin.getPrice() + "")));
            List<Map<String, String>> historylist = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<WellethistoryState> history = wellethistoryStateService.selectByWelletId(Integer.parseInt(fId), type1, page1, size1);
            for (WellethistoryState wellethistoryState : history) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("type", wellethistoryState.getType() + "");
                map1.put("Id", wellethistoryState.getFid() + "");
                map1.put("remark", wellethistoryState.getRemark());
                map1.put("num", BigDecimalUtils.getBigDecimalStr(wellethistoryState.getChangenum()));
                map1.put("statetype", wellethistoryState.getState() + "");
                if (wellethistoryState.getState() == 1) {
                    map1.put("state", "已完成");
                } else {
                    map1.put("state", "进行中");
                }
                map1.put("time", format.format(wellethistoryState.getCreatetime()));
                historylist.add(map1);
            }
            json.put("history", historylist);
            json.put("msg", "ok");
            json.put("data", welletjson);
            json.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }


    /***
     * 个人中心账单
     *
     * */
    @RequestMapping("/app/CZTX")
    public String CZTX(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String size = map.get("size");
        List<Map<String, String>> historylist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WellethistoryState> history = wellethistoryStateService.selectBycztx(welletService.selectByUserKey(getUserKey(), 1).getFid(), Integer.parseInt(page), Integer.parseInt(size));
        for (WellethistoryState wellethistoryState : history) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("type", wellethistoryState.getType() + "");
            map1.put("num", wellethistoryState.getChangenum() + "");
            map1.put("coin", wellethistoryState.getCointype() + "");
            map1.put("time", format.format(wellethistoryState.getCreatetime()));
            historylist.add(map1);
        }
        json.put("msg", "ok");
        json.put("data", historylist);
        json.put("code", 200);
        return json.toJSONString();
    }


    @RequestMapping("/app/transferhistory")
    public String transferhistory(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String size = map.get("size");
        List<Map<String, String>> historylist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WellethistoryState> history = wellethistoryStateService.selectBytransfer(welletService.selectByUserKey(getUserKey(), 1).getFid(), Integer.parseInt(page), Integer.parseInt(size));
        for (WellethistoryState wellethistoryState : history) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("type", wellethistoryState.getType() + "");
            map1.put("num", wellethistoryState.getChangenum() + "");
            map1.put("coin", wellethistoryState.getCointype() + "");
            map1.put("time", format.format(wellethistoryState.getCreatetime()));
            historylist.add(map1);
        }
        json.put("msg", "ok");
        json.put("data", historylist);
        json.put("code", 200);
        return json.toJSONString();
    }


    @RequestMapping("/app/bbjy")
    public String bbjy(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String size = map.get("size");
        List<Map<String, String>> historylist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MappingTrade mappingTrade = new MappingTrade();
        mappingTrade.setFuser(getUserKey());
        mappingTrade.setState(2);
        List<MappingTrade> list = mappingTradeService.selectBy(mappingTrade, Integer.parseInt(page), Integer.parseInt(size));
        for (MappingTrade mappingTrade1 : list) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("type", mappingTrade1.getType() + "");
            if (mappingTrade1.getType() == 1) {
                map1.put("coinnum1", (mappingTrade1.getTradenum().doubleValue() / mappingTrade1.getPrice().doubleValue() + mappingTrade1.getFee().doubleValue()) + "");
                map1.put("coinmun2", mappingTrade1.getTradenum().doubleValue() + "");
                map1.put("fee", mappingTrade1.getFee().doubleValue() + "");
            } else if (mappingTrade1.getType() == 2) {
                map1.put("coinnum1", (mappingTrade1.getTradenum().doubleValue() + mappingTrade1.getFee().doubleValue()) + "");
                map1.put("coinmun2", mappingTrade1.getTradenum().doubleValue() / mappingTrade1.getPrice().doubleValue() + "");
                map1.put("fee", mappingTrade1.getFee().doubleValue() + "");
            }
            map1.put("updatetime", format.format(mappingTrade1.getUpdatetime()));
            historylist.add(map1);
        }

        json.put("msg", "ok");
        json.put("data", historylist);
        json.put("code", 200);
        return json.toJSONString();
    }


    @RequestMapping("/app/reward")
    public String reward(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String page = map.get("page");
        String size = map.get("size");
        List<Map<String, String>> historylist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Map> history = coinLogMapper.selectByUserpage1(getUserKey(), (Integer.parseInt(page) - 1) * Integer.parseInt(size), Integer.parseInt(size));
        for (Map coinlog : history) {
            Map<String, String> map1 = new HashMap<>();
            map1.put("type", coinlog.get("change_type") + "");
            map1.put("Cointype2", coinlog.get("change_coin") + "");
            map1.put("coinNum2", coinlog.get("change_amount") + "");
            map1.put("time", format.format(new Date(Long.valueOf(coinlog.get("change_time") + "") * 1000L)));
            historylist.add(map1);
        }

        json.put("msg", "ok");
        json.put("data", historylist);
        json.put("code", 200);
        return json.toJSONString();
    }

    /**
     * 获取账单列表
     */
    @RequestMapping("/app/gethistorylist")
    public String getHistoryList(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String fId = map.get("welletfid");
        String type = map.get("type");
        String page = map.get("page");
        String size = map.get("size");
        if (fId == null || "".equals(fId)) {
            json.put("msg", "参数缺失！");
            json.put("code", 300);
            return json.toJSONString();
        }
        Integer type1 = null;
        Integer page1 = null;
        Integer size1 = null;
        try {
            if (type != null && !"".equals(type)) {
                type1 = Integer.parseInt(type);
            }
            if (page != null && !"".equals(page)) {
                page1 = Integer.parseInt(page);
            }
            if (size != null && !"".equals(size)) {
                size1 = Integer.parseInt(size);
            }
            List<WellethistoryState> history = wellethistoryStateService.selectByWelletId(Integer.parseInt(fId), type1, page1, size1);
            List<Map<String, String>> historylist = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (WellethistoryState wellethistoryState : history) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("type", wellethistoryState.getType() + "");
                map1.put("Id", wellethistoryState.getFid() + "");
                map1.put("remark", wellethistoryState.getRemark());
                map1.put("num", wellethistoryState.getChangenum() + "");
                if (wellethistoryState.getState() == 1) {
                    map1.put("state", "已完成");
                } else {
                    map1.put("state", "进行中");
                }
                map1.put("statetype", wellethistoryState.getState() + "");
                map1.put("time", format.format(wellethistoryState.getCreatetime()));
                historylist.add(map1);
            }
            List<Map> history2 = coinLogMapper.selectByUserpage(welletService.selectByPrimaryKey(Integer.parseInt(fId)).getCoinid(), getUserKey(), page1, size1);
            for (Map changelog1 : history2) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("type", changelog1.get("change_type") + "");
                map1.put("Id", changelog1.get("id") + "");
                map1.put("remark", changelog1.get("change_desc") + "");
                map1.put("num", changelog1.get("change_amount") + "");
                map1.put("state", "已完成");
                map1.put("time", format.format(new Date(Long.valueOf(changelog1.get("change_time") + "") * 1000L)));
                historylist.add(map1);
            }
            json.put("msg", "ok");
            json.put("history", historylist);
            json.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 201);
        }
        return json.toJSONString();
    }

    /**
     * 获取账单详情
     */
    @RequestMapping("/app/gethistorydetail")
    public String getHistorydetail(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String historyId = map.get("historyId");
        if (historyId == null || "".equals(historyId)) {
            json.put("msg", "参数缺失！");
            json.put("code", 300);
            return json.toJSONString();
        }
        WellethistoryState history = wellethistoryStateService.selectByPrimaryKey(Integer.parseInt(historyId));
        if (history == null) {
            json.put("msg", "记录不存在！");
            json.put("code", 204);
            return json.toJSONString();
        }
        Orders orders = ordersMapper.queryorderId(history.getKeyes());
        Map<String, String> historymap = new HashMap<>();
        historymap.put("num", history.getChangenum() + "");
        historymap.put("coin", history.getCointype() + "");
        historymap.put("type", history.getType() + "");
        historymap.put("state", history.getState() + "");
        historymap.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(history.getUpdatetime()));
        if (orders != null) {
            historymap.put("keys", orders.getBuyadress());
        }
        json.put("history", historymap);
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    /**
     * 充币
     */
    @RequestMapping("/app/charge")
    public String charge() {
        JSONObject json = new JSONObject();
        Wellets ETH = welletService.selectByUserKey(getUserKey(), 1);//ETH
        Wellets AGC = welletService.selectByUserKey(getUserKey(), 2);//AGC
        Wellets USDT = welletService.selectByUserKey(getUserKey(), 6);//USDT
        Wellets integral = welletService.selectByUserKey(getUserKey(), 4);//integral
        addree_user addree_user = addressDao.selectByUserKey(getUserKey());
        String adress = "";

        if (StringUtils.isEmpty(ETH.getAddress()) || "无".equals(ETH.getAddress())) {
            adress = EthUtilHuang.createEthAddress(MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + getUserKey()))));
            addree_user.setAdress(adress);
            addressDao.updateByPrimaryKeySelective(addree_user);
            ETH.setAddress(adress);
            welletsMapper.updateByPrimaryKeySelective(ETH);
            AGC.setAddress(adress);
            welletsMapper.updateByPrimaryKeySelective(AGC);
            USDT.setAddress(adress);
            welletsMapper.updateByPrimaryKeySelective(USDT);
            integral.setAddress(adress);
            welletsMapper.updateByPrimaryKeySelective(integral);
        }

        json.put("addressEth", ETH.getAddress());
        json.put("addressAgc", AGC.getAddress());
        json.put("addressUsdt", USDT.getAddress());
        json.put("msg", "ok");
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/app/wallet/getbalance")
    public String getwalletbalance(@RequestBody JSONObject param) {
        Integer coin = param.getInteger("coin");
        Wellets wellet = welletService.selectByUserandcoin(getUserKey(), coin);
        Coins coins = coinsMapper.selectByPrimaryKey(coin);
        Coins usdt = coinsMapper.selectByPrimaryKey(6);
        Map map = new HashMap();
        double price = usdt.getPrice();
        if (coin.intValue() != 6) {
            price = coins.getPrice() * price;
        }
        BigDecimal coinnum = wellet.getCoinnum();
        if (coinnum.compareTo(BigDecimal.ZERO) == 0) {
            map.put("balance", 0);
        } else {
            map.put("balance", coinnum.stripTrailingZeros().toPlainString());
        }
        map.put("price", price);
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }


    private static Object obj = new Object();

    /**
     * 提币
     **/
    @RequestMapping("/app/withdraw")
    @Transactional
    public String withdraw(@RequestBody Map<String, String> map) {
        int coin = 0;
        JSONObject json = new JSONObject();
        coin = Integer.parseInt(map.get("coin"));

        if (redisDao.getValue("tb" + getUserKey()) != null) {
            json.put("msg", "交易异常，请五分钟后重试");
            json.put("code", 300);
            return json.toJSONString();
        }

        String address = map.get("address");
        String num = map.get("num");
//        if (Double.parseDouble(num) % 100 != 0 || Double.parseDouble(num) < 100) {
//            json.put("msg", "提币金额必须是100的整数倍");
//            json.put("code", 203);
//            return json.toJSONString();
//        }

        String tradepwd = map.get("tradepwd");
        if (address == null || tradepwd == null || "".equals(tradepwd) || "".equals(address) || "".equals(num)) {
            json.put("msg", "参数缺失");
            json.put("code", 203);
            return json.toJSONString();
        }
        BigDecimal coinNum = new BigDecimal(num);
        BigDecimal gas;//手续费
        Wellets wellet;
        synchronized (obj) {
            if (coin != 3) {
                if (!address.startsWith("0x") && address.length() != 42) {
                    json.put("msg", "该地址不合法");
                    json.put("code", 300);
                    return json.toJSONString();
                }
            }
            wellet = welletService.selectByUserKey(getUserKey(), coin);
            if (address.equals(wellet.getAddress())) {
                json.put("msg", "该地址为当前钱包地址");
                json.put("code", 300);
                return json.toJSONString();
            }
            ParametersWithBLOBs serviceCharge = parametersService.findByKey("serviceCharge");//获取手续费比例
            gas = new BigDecimal(serviceCharge.getKeyvalue()).multiply(coinNum);//手续费
            if (wellet.getCoinnum().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal bigDecimal = coinNum.add(gas);//加上手续费
                if (wellet.getCoinnum().compareTo(bigDecimal) < 0) {
                    json.put("msg", "余额不足");
                    json.put("code", 300);
                    return json.toJSONString();
                }
            } else {
                json.put("msg", "余额不足");
                json.put("code", 300);
                return json.toJSONString();
            }
        }

        int count = ordersMapper.countByStateAndUsersell(-1, getUserKey());
        if (count > 0) {
            json.put("msg", "还有提币请求处理中");
            json.put("code", 300);
            redisDao.remove("tb" + getUserKey());
            return json.toJSONString();
        }

        int count1 = wellethistoryStateService.querywithdraw(Integer.valueOf(getUserKey()), coin);
        if (count1 > 0) {
            json.put("msg", "还有提币处理中");
            json.put("code", 300);
            return json.toJSONString();
        }

        String key = String.format("%s%s", ConfigConst.KEY_PASS, getUserKey());

        Boolean hasKey = redisDao.hasKey(key);

        if (hasKey) {
            Integer errornum = Integer.valueOf(redisDao.getValue(key));
            //每日限制交易密码只能错误三次
            if (errornum >= 3) {
                json.put("msg", "交易密码错误3次,请明日再试");
                json.put("code", 300);
                redisDao.setKey("tb" + getUserKey(), "123");
                return json.toJSONString();
            }
        }

        if (!tradepwd.trim().equals(usersService.selectByPrimaryKey(Integer.valueOf(getUserKey())).getTranspassword())) {
            redisDao.increment(key);
            if (!hasKey) {
                //初始化过期时间
                redisDao.setTime(key, DateUtil.nextDay() / 1000);
            }
            json.put("msg", "交易密码错误");
            json.put("code", 300);
            return json.toJSONString();
        }

        //密码正确，清除错误密码累计
        if (hasKey) {
            redisDao.remove(key);
        }

        // 提现比例限制
        BigDecimal withdrawalRestrictions = new BigDecimal(1);
        String wrMsg = "触发风控系统,无法全部提现,请联系客服";
        ParametersWithBLOBs wrBigDecimal = parametersMapper.selectByKey("withdrawalRestrictions");
        ParametersWithBLOBs wrMsgStr = parametersMapper.selectByKey("wrMsg");
        if (wrBigDecimal != null) {
            withdrawalRestrictions = new BigDecimal(wrBigDecimal.getKeyvalue());
        }
        if (wrMsgStr != null) {
            wrMsg = wrMsgStr.getKeyvalue();
        }

        // 限制额度
        double wr = withdrawalRestrictions.multiply(new BigDecimal(wellet.getCoinnum().doubleValue())).doubleValue();

        if (Double.parseDouble(num) > wr) {
            json.put("msg", wrMsg);
            json.put("code", 300);
            return json.toJSONString();
        }

        String transpassword = usersService.selectByPrimaryKey(Integer.valueOf(getUserKey())).getTranspassword();
        welletService.withdraw1(getUserKey(), transpassword, coin, coinNum, gas, address);
        json.put("msg", "提币请求已提交，等待后台审核");
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/app/getgasPrice")
    public String getgasPrice(@RequestBody JSONObject param) {
        EthApiModel ethApiModel = EthUtil.getGasPrice2();
        Map<String, Object> map = new HashMap<>();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(18);
        map.put("mingas", ethApiModel.getSafeLow());
        map.put("centgas", ethApiModel.getFast());
        map.put("maxgas", ethApiModel.getFastest());
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    @RequestMapping("/app/transfer")
    public String transfer(@RequestBody Map<String, String> map) {
        int type = Integer.parseInt(map.get("coin"));
        double num = Double.parseDouble(map.get("num"));
        Wellets wellet = welletService.selectByUserKey(getUserKey(), type);
        int i = 0;
        if (wellet.getReleasenum().doubleValue() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "可用数量为零！"));
        }
        if (wellet.getReleasenum().doubleValue() <= num) {
            return JSONObject.toJSONString(new ResultDTO(-1, "可用数量不足！"));
        }
        i = welletService.transfer(type, getUserKey(), num);
        if (i > 0) {
            return JSONObject.toJSONString(new ResultDTO(200, "ok"));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "error"));
    }

    @RequestMapping("/app/gettransfer")
    public String gettransfer(@RequestBody Map<String, String> map) {
        int coinId = Integer.parseInt(map.get("coin"));
        Wellets wellet = welletService.selectByUserKey(getUserKey(), coinId);
        if (wellet != null) {
            Map<String, String> map1 = new HashMap<>();
            //map1.put("coinNum",wellet.getCoinnum()+"");
            map1.put("releaseNum", wellet.getReleasenum() + "");
            return JSONObject.toJSONString(new ResultDTO(200, "ok", map1));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "error"));
    }

    @RequestMapping("/app/lockwellet")
    public String lockwellet() {
        Wellets wellet = welletService.selectByUserKey(getUserKey(), 2);
        if (wellet != null) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("lockNum", wellet.getLocknum() + "");
            map1.put("releaseNum", wellet.getReleasenum() + "");
            List<Map> list = new ArrayList<>();
            Map<String, String> map2 = new HashMap<>();
            map2.put("type", "1");
            map2.put("time", "2019-06-17 16:26:52");
            map2.put("num", 12.36 + "");
            list.add(map2);
            map1.put("history", list);
            return JSONObject.toJSONString(new ResultDTO(200, "ok", map1));
        }
        return JSONObject.toJSONString(new ResultDTO(-1, "error"));
    }

    @RequestMapping("/app/queryrate")
    public String queryrate(@RequestBody JSONObject param) {
        Coins coin1 = coinsMapper.selectByPrimaryKey(param.getIntValue("coin1"));
        Coins coin2 = coinsMapper.selectByPrimaryKey(param.getIntValue("coin2"));
        Map map = new HashMap();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(18);
        if (coin1 == null || coin2 == null) {
            map.put("rate", 0.0);
            map.put("coin1price", 0.0);
            map.put("coin2price", 0.0);
            return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
        }
        map.put("rate", nf.format(coin1.getPrice() / coin2.getPrice()).replace(",", ""));
        map.put("coin1price", coin1.getPrice());
        map.put("coin2price", coin2.getPrice());
        return JSONObject.toJSONString(new ResultDTO(200, "ok", map));
    }

    /**
     * 闪兑
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/coinexchange")
    @Transactional
    public String coinexchange(@RequestBody JSONObject param) {
        String pwd = param.getString("transpassword");
        Integer coin1Id = param.getIntValue("coin1");
        Integer coin2Id = param.getIntValue("coin2");
        if (coin1Id.equals(coin2Id)) {
            return JSONObject.toJSONString(new ResultDTO(-1, "The same currency"));
        }
        if (coin1Id.equals(2)) {
            if (coin2Id.equals(1)) {
                return JSONObject.toJSONString(new ResultDTO(-1, "AGC闪兑ETH，暂未开放"));
            } else if (coin2Id.equals(6)) {
                return JSONObject.toJSONString(new ResultDTO(-1, "AGC闪兑USDT，暂未开放"));
            }
        }
        Users user = usersService.selectByPrimaryKey(getUserId());
        if (!pwd.equals(user.getTranspassword())) {
            return JSONObject.toJSONString(new ResultDTO(-1, "Password error"));
        }
        double coinnum = param.getDoubleValue("coinnum");
        if (coinnum <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "Quantity must be greater than 0"));
        }
        Coins coin1 = coinsMapper.selectByPrimaryKey(coin1Id);
        Coins coin2 = coinsMapper.selectByPrimaryKey(coin2Id);
        Wellets wellet1 = welletService.selectByUserandcoin(getUserKey(), coin1.getFid());
        if (wellet1.getCoinnum().doubleValue() <= 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "Sorry, your credit is running low"));
        }
        Wellets wellet2 = welletService.selectByUserandcoin(getUserKey(), coin2.getFid());
        double coinsum = coin1.getPrice() * coinnum / coin2.getPrice();
        wellet1.setCoinnum(BigDecimal.valueOf(wellet1.getCoinnum().doubleValue() - coinnum));
        wellet2.setCoinnum(BigDecimal.valueOf(wellet2.getCoinnum().doubleValue() + coinsum));
        if (wellet1.getCoinnum().doubleValue() < 0 || wellet2.getCoinnum().doubleValue() < 0) {
            return JSONObject.toJSONString(new ResultDTO(-1, "The amount of exchange is too large"));
        }
        CheckEx.error(welletService.updateByPrimaryKeySelective(wellet1) < 1);
        CheckEx.error(welletService.updateByPrimaryKeySelective(wellet2) < 1);

        //保存两条交易记录，咱币种那里显示为闪兑
        WellethistoryState wellethistoryState1 = new WellethistoryState();
        wellethistoryState1.setBalance(new BigDecimal(-coinnum));
        wellethistoryState1.setChangenum(new BigDecimal(-coinnum));
        wellethistoryState1.setCointype(wellet1.getCoinid());
        wellethistoryState1.setCreatetime(new Date());
        wellethistoryState1.setKeyes(UUID.randomUUID().toString());
        wellethistoryState1.setRecharge(wellet1.getAddress());
        wellethistoryState1.setState(1);
        wellethistoryState1.setType(12);
        wellethistoryState1.setUpdatetime(new Date());
        wellethistoryState1.setWelletid(wellet1.getFid());
        wellethistoryState1.setWithdraw(null);
        wellethistoryState1.setRemark("闪兑");
        wellethistoryState1.setUserid(Integer.parseInt(wellet1.getUserkey()));
        CheckEx.error(wellethistoryStateMapper.insertSelective(wellethistoryState1) < 1);

        WellethistoryState wellethistoryState2 = new WellethistoryState();
        wellethistoryState2.setBalance(new BigDecimal(coinsum));
        wellethistoryState2.setChangenum(new BigDecimal(coinsum));
        wellethistoryState2.setCointype(wellet2.getCoinid());
        wellethistoryState2.setCreatetime(new Date());
        wellethistoryState2.setKeyes(UUID.randomUUID().toString());
        wellethistoryState2.setRecharge(wellet2.getAddress());
        wellethistoryState2.setState(1);
        wellethistoryState2.setType(12);
        wellethistoryState2.setUpdatetime(new Date());
        wellethistoryState2.setWelletid(wellet2.getFid());
        wellethistoryState2.setWithdraw(null);
        wellethistoryState2.setRemark("闪兑");
        wellethistoryState2.setUserid(Integer.parseInt(wellet2.getUserkey()));
        CheckEx.error(wellethistoryStateMapper.insertSelective(wellethistoryState2) < 1);
        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
    }

    //创建地址
    @RequestMapping("/app/createCoinAddress")
    public String createCoinAddress() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                int number = 10000;
                ParametersWithBLOBs addAddressNumber = parametersMapper.selectByKey("addAddressNumber");
                if (addAddressNumber != null) {
                    number = Integer.parseInt(addAddressNumber.getKeyvalue());
                }
                for (int i = 0; i < number; i++) {
                    String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
                    String pwd = MD5Util.getThreeMD5String(ConfigConst.Turn_PWD + uuid);
                    String adress = EthUtilHuang.createEthAddress(pwd);
                    if (!StringUtils.isEmpty(adress)) {
                        CoinAddressStoreEntity coinAddressStoreEntity = new CoinAddressStoreEntity();
                        coinAddressStoreEntity.setAddress(adress);
                        coinAddressStoreEntity.setCoinName("ETH");
                        coinAddressStoreEntity.setCoinId(1);
                        coinAddressStoreEntity.setStatus(0);
                        coinAddressStoreEntity.setPwd(pwd);
                        coinAddressStoreEntity.setCreateTime(new Date());
                        coinAddressStoreEntity.setUpdateTime(new Date());
                        coinAddressStoreMapper.insert(coinAddressStoreEntity);
                    }
                }
            }
        });
        return JSONObject.toJSONString(new ResultDTO(200, "success"));
    }
}
