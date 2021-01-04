//package com.lanjing.wallet.timer;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.lanjing.wallet.config.ConfigConst;
//import com.lanjing.wallet.dao.*;
//import com.lanjing.wallet.model.*;
//import com.lanjing.wallet.service.RecommendService;
//import com.lanjing.wallet.util.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@RestController
//public class Recharge {
//
//    @Resource(name = "RecommendService")
//    private RecommendService recommendService;
//
//    @Autowired
//    private TrasferlistMapper trasferlistMapper;
//
//    @Autowired
//    private BtctrasferlistMapper btctrasferlistMapper;
//
//    @Autowired
//    private TrasferTokenlistMapper trasferTokenlistMapper;
//
//    @Autowired
//    private WelletsMapper welletsMapper;
//
//    @Autowired
//    private UsersMapper usersMapper;
//
//    @Autowired
//    private OrdersMapper ordersMapper;
//
//    @Autowired
//    private WellethistoryStateMapper wellethistoryStateMapper;
//
//    @Autowired
//    private IncomeMapper incomeMapper;
//
//    @Autowired
//    AirdropRewardMapper airdropRewardMapper;
//
//    @Autowired
//    private RedisDao redisDao;
//
//    @Autowired
//    private WithdrawLogMapper withdrawLogMapper;
//
//    @Autowired
//    private ParametersMapper parametersMapper;
//
//
//    /**
//     * 定时获取ETH充值记录
//     */
//    //@Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void getrechargelist() {
//        int maxId = trasferlistMapper.selectBymaxId();
//        if (maxId <= 0) {
//            maxId = 1;
//        } else {
//            maxId = maxId + 1;
//        }
//        String back = null;
//        try {
//            back = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/eth/incomePage/" + maxId + "/" + 10);
//
//            Map<String, Object> json = JSONObject.parseObject(back, Map.class);
//            Map<String, Object> map1 = (Map<String, Object>) json.get("data");
//            List list = (List) map1.get("tx_incomes");
//            if (list != null) {
//                for (Object obj : list) {
//                    Map map = (Map) obj;
//                    int id = (int) map.get("id");
//                    String txHash = (String) map.get("txHash");
//                    String createTime = (String) map.get("createTime");
//                    String to = (String) map.get("to");
//                    double amount = new BigDecimal(map.get("amount") + "").doubleValue();
//                    Trasferlist trasferlist = new Trasferlist();
//                    trasferlist.setFid(id);
//                    trasferlist.setTxid(txHash);
//                    trasferlist.setMemo(to);
//                    trasferlist.setTradenum(amount);
//                    Map txId = wellethistoryStateMapper.queryByKeyes(txHash);
//                    if (txId != null) {
//                        int type = Integer.parseInt(txId.get("type") + "");
//                        if (type == 2) {
//                            trasferlist.setType(1);
//                        } else {
//                            trasferlist.setType(type);
//                        }
//                    } else {
//                        trasferlist.setType(1);
//                    }
//                    trasferlistMapper.insertSelective(trasferlist);
//                }
//            }
//        } catch (IOException e) {
//            // e.printStackTrace();
//        }
//    }
//
//    // @Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void getbtcrechargelist() {
//        int maxId = btctrasferlistMapper.selectBymaxId();
//        // System.out.println("btc-------"+maxId);
//        if (maxId <= 0) {
//            maxId = 1;
//        } else {
//            maxId = maxId + 1;
//        }
//        String back = null;
//        try {
//            back = HttpUtil.sendGet(ConfigConst.BTCNODE_ADDRESS + "/btc/incomePage/" + maxId + "/" + 10);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> json = JSONObject.parseObject(back, Map.class);
//        Map<String, Object> map1 = (Map<String, Object>) json.get("data");
//        List list = (List) map1.get("tx_incomes");
//        if (list != null) {
//            for (Object obj : list) {
//                Map map = (Map) obj;
//                int id = (int) map.get("id");
//                String txHash = (String) map.get("txId");
//                String createTime = (String) map.get("createTime");
//                String to = (String) map.get("to");
//                double amount = new BigDecimal(map.get("amount") + "").doubleValue();
//                Btctrasferlist trasferlist = new Btctrasferlist();
//                trasferlist.setFid(id);
//                trasferlist.setTxid(txHash);
//                trasferlist.setMemo(to);
//                trasferlist.setTradenum(amount);
//                Map txId = wellethistoryStateMapper.queryByKeyes(txHash);
//                if (txId != null) {
//                    int type = Integer.parseInt(txId.get("type") + "");
//                    if (type == 2) {
//                        trasferlist.setType(1);
//                    } else {
//                        trasferlist.setType(type);
//                    }
//                } else {
//                    trasferlist.setType(1);
//                }
//                btctrasferlistMapper.insertSelective(trasferlist);
//            }
//        }
//    }
//
//
//    //@Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void gettokenrechargelist() {
//        try {
//            int maxId = trasferTokenlistMapper.selectBymaxId(2);
//            if (maxId <= 0) {
//                maxId = 1;
//            } else {
//                maxId = maxId + 1;
//            }
//            String back = null;
//
//            back = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/token/incomePage/" + ConfigConst.PLATFORM_ADDRESS + "/" + maxId + "/" + 10);
//
//            Map<String, Object> json = JSONObject.parseObject(back, Map.class);
//            Map<String, Object> map1 = (Map<String, Object>) json.get("data");
//            List list = (List) map1.get("tx_incomes");
//            if (list != null) {
//                for (Object obj : list) {
//                    Map map = (Map) obj;
//                    int id = (int) map.get("id");
//                    String txHash = (String) map.get("txHash");
//                    String createTime = (String) map.get("createTime");
//                    String to = (String) map.get("to");
//                    double amount = new BigDecimal(map.get("amount") + "").doubleValue();
//                    Trasferlist trasferlist = new Trasferlist();
//                    trasferlist.settId(id);
//                    trasferlist.setCoin(5);
//                    trasferlist.setTxid(txHash);
//                    trasferlist.setMemo(to);
//                    trasferlist.setTradenum(amount);
//                    Map txId = wellethistoryStateMapper.queryByKeyes(txHash);
//                    if (txId != null) {
//                        int type = Integer.parseInt(txId.get("type") + "");
//                        if (type == 2) {
//                            trasferlist.setType(1);
//                        } else {
//                            trasferlist.setType(type);
//                        }
//                    } else {
//                        trasferlist.setType(1);
//                    }
//                    trasferTokenlistMapper.insertSelective(trasferlist);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // @Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void gettokenrechargelist1() {
//        try {
//            int maxId = trasferTokenlistMapper.selectBymaxId(6);
//            if (maxId <= 0) {
//                maxId = 1;
//            } else {
//                maxId = maxId + 1;
//            }
//            String back = null;
//            back = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/token/incomePage/" + ConfigConst.PLATFORM_ADDRESS1 + "/" + maxId + "/" + 10);
//            Map<String, Object> json = JSONObject.parseObject(back, Map.class);
//            Map<String, Object> map1 = (Map<String, Object>) json.get("data");
//            List list = (List) map1.get("tx_incomes");
//            if (list != null) {
//                for (Object obj : list) {
//                    Map map = (Map) obj;
//                    int id = (int) map.get("id");
//                    String txHash = (String) map.get("txHash");
//                    String createTime = (String) map.get("createTime");
//                    String to = (String) map.get("to");
//                    double amount = new BigDecimal(map.get("amount") + "").doubleValue();
//                    Trasferlist trasferlist = new Trasferlist();
//                    trasferlist.settId(id);
//                    trasferlist.setCoin(6);
//                    trasferlist.setTxid(txHash);
//                    trasferlist.setMemo(to);
//                    trasferlist.setTradenum(amount);
//                    Map txId = wellethistoryStateMapper.queryByKeyes(txHash);
//                    if (txId != null) {
//                        int type = Integer.parseInt(txId.get("type") + "");
//                        if (type == 2) {
//                            trasferlist.setType(1);
//                        } else {
//                            trasferlist.setType(type);
//                        }
//                    } else {
//                        trasferlist.setType(1);
//                    }
//                    trasferTokenlistMapper.insertSelective(trasferlist);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*@Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void gettokenrechargelist2 (){
//        int maxId = trasferTokenlistMapper.selectBymaxId(7);
//        System.out.println("-------"+maxId);
//        if(maxId <= 0){
//            maxId = 1;
//        }else{
//            maxId = maxId +1;
//        }
//        String back = null;
//        try {
//            back = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS+"/token/incomePage/"+ ConfigConst.ETPLATFORM_ADDRESS +"/"+maxId+"/"+10);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Map<String,Object> json = JSONObject.parseObject(back,Map.class);
//        Map<String,Object> map1 = (Map<String, Object>) json.get("data");
//        List list = (List)map1.get("tx_incomes");
//        if(list != null){
//            for (Object obj : list){
//                Map map = (Map) obj;
//                int id = (int) map.get("id");
//                String txHash = (String) map.get("txHash");
//                String createTime = (String) map.get("createTime");
//                String to = (String) map.get("to");
//                double amount = new BigDecimal(map.get("amount")+"").doubleValue();
//                Trasferlist trasferlist = new Trasferlist();
//                trasferlist.settId(id);
//                trasferlist.setCoin(7);
//                trasferlist.setTxid(txHash);
//                trasferlist.setMemo(to);
//                trasferlist.setTradenum(amount);
//                Map txId = wellethistoryStateMapper.queryByKeyes(txHash);
//                if(txId != null){
//                    int type = Integer.parseInt(txId.get("type")+"");
//                    if(type == 2){
//                        trasferlist.setType(1);
//                    }else {
//                        trasferlist.setType(type);
//                    }
//                }else{
//                    trasferlist.setType(1);
//                }
//                trasferTokenlistMapper.insertSelective(trasferlist);
//            }
//        }
//    }*/
//
//
////    @Scheduled(fixedRate = 1000 * 60, initialDelay = 3000)
//    public void Recharge() {
//        List<Trasferlist> list = trasferlistMapper.selectBystate(0);
//        for (Trasferlist trasferlist : list) {
//            if (trasferlist.getMemo() != null && !"".equals(trasferlist.getMemo())) {
//                Wellets wellet = welletsMapper.selectByAddressAndCoin(trasferlist.getMemo(), 1);
//                if (wellet != null) {
//                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                    int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                    WellethistoryState wellethistoryState2 = new WellethistoryState();
//                    wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    wellethistoryState2.setType(1);
//                    wellethistoryState2.setRemark("充币");
//                    Date time = new Date();
//                    wellethistoryState2.setCreatetime(time);
//                    wellethistoryState2.setUpdatetime(time);
//                    wellethistoryState2.setWelletid(wellet.getFid());
//                    wellethistoryState2.setState(1);
//                    wellethistoryState2.setCointype(wellet.getCoinid());
//                    wellethistoryState2.setKeyes(trasferlist.getTxid());
//                    wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                    Orders order = new Orders();
//                    order.setOrderid(trasferlist.getTxid());
//                    order.setTradecoin("ETH");
//                    order.setTradecoinid(1);
//                    order.setCreatetime(new Date());
//                    order.setConfirmtime(new Date());
//                    order.setBuyadress(trasferlist.getMemo());
//                    order.setUserbuy(wellet.getUserkey());
//                    order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    order.setUsersell("");
//                    order.setState(1);
//                    if (j > 0) {
//                        ordersMapper.insertSelective(order);
//                        int i = wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                        if (i > 0) {
//                            trasferlistMapper.updateByfId(trasferlist.getFid());
//                            long fee = (long) EthUtil.getGasPrice();
//                            if (trasferlist.getTradenum() - (fee * 1.1 * 21000.0 / 1000000000000000000l) > 0) {
//                                System.out.println("汇总到主账号" + (trasferlist.getTradenum() - (fee * 21000.0 / 1000000000000000000l)));
//                                String result = EthUtil.ethsend(wellet.getAddress(), ConfigConst.Receive_ADDRESS, trasferlist.getTradenum() - (fee * 1.1 * 21000.0 / 1000000000000000000l), MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD + wellet.getUserkey()))), fee);
//                                Orders order1 = new Orders();
//                                order1.setOrderid(trasferlist.getTxid());
//                                order1.setTradecoin("ETH");
//                                order1.setTradecoinid(1);
//                                order1.setCreatetime(new Date());
//                                order1.setConfirmtime(new Date());
//                                order1.setBuyadress(ConfigConst.Receive_ADDRESS);
//                                order1.setUserbuy(String.valueOf(0000000));
//                                order1.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum() - (fee * 1.1 * 21000.0 / 1000000000000000000l)));
//                                order1.setUsersell("000" + wellet.getUserkey());
//                                order1.setState(1);
//                                ordersMapper.insertSelective(order);
//                            }
//                        }
//                    }
//                    /*long fee = (long) EthUtil.getGasPrice();
//                    System.out.println("汇总到主账号"+(trasferlist.getTradenum()-(fee*21000.0/1000000000000000000l)));
//                    String result = EthUtil.ethsend(wellet.getAddress(),ConfigConst.Receive_ADDRESS,trasferlist.getTradenum()-(fee*21000.0/1000000000000000000l),MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+wellet.getUserkey()))), fee);
//                    if(result == null || "null".equals(result)){
//                        System.out.println("汇总到主账号失败");
//                        trasferlistMapper.updateByfId2(trasferlist.getFid());
//                    }else {
//                        wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                        int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                        WellethistoryState wellethistoryState2 = new WellethistoryState();
//                        wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                        wellethistoryState2.setType(1);
//                        wellethistoryState2.setRemark("充币");
//                        Date time = new Date();
//                        wellethistoryState2.setCreatetime(time);
//                        wellethistoryState2.setUpdatetime(time);
//                        wellethistoryState2.setWelletid(wellet.getFid());
//                        wellethistoryState2.setState(1);
//                        wellethistoryState2.setCointype(wellet.getCoinid());
//                        wellethistoryState2.setKeyes(trasferlist.getTxid());
//                        wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                        Orders order = new Orders();
//                        order.setOrderid(trasferlist.getTxid());
//                        order.setTradecoin("ETH");
//                        order.setTradecoinid(1);
//                        order.setCreatetime(new Date());
//                        order.setConfirmtime(new Date());
//                        order.setBuyadress(trasferlist.getMemo());
//                        order.setUserbuy(wellet.getUserkey());
//                        order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                        order.setUsersell("");
//                        order.setState(1);
//                        ordersMapper.insertSelective(order);
//                        int i =wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                        if(i > 0){
//                            trasferlistMapper.updateByfId(trasferlist.getFid());
//                        }
//                    }*/
//                    /*int g = transferfee(wellet.getAddress(),fee);
//                    if(g > 0){
//                        System.out.println("汇总到主账号");
//                        String result = EthUtil.ethsend(wellet.getAddress(),ConfigConst.Receive_ADDRESS,trasferlist.getTradenum(),ConfigConst.Turn_PWD, fee);
//                        if(result == null || "null".equals(result)){
//                            System.out.println("汇总到主账号失败");
//                            trasferlistMapper.updateByfId2(trasferlist.getFid());
//                        }else {
//                            wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                            int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                            WellethistoryState wellethistoryState2 = new WellethistoryState();
//                            wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                            wellethistoryState2.setType(1);
//                            wellethistoryState2.setRemark("充币");
//                            Date time = new Date();
//                            wellethistoryState2.setCreatetime(time);
//                            wellethistoryState2.setUpdatetime(time);
//                            wellethistoryState2.setWelletid(wellet.getFid());
//                            wellethistoryState2.setState(1);
//                            wellethistoryState2.setCointype(wellet.getCoinid());
//                            wellethistoryState2.setKeyes(trasferlist.getTxid());
//                            wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                            Orders order = new Orders();
//                            order.setOrderid(trasferlist.getTxid());
//                            order.setTradecoin("ETH");
//                            order.setTradecoinid(1);
//                            order.setCreatetime(new Date());
//                            order.setConfirmtime(new Date());
//                            order.setBuyadress(trasferlist.getMemo());
//                            order.setUserbuy(wellet.getUserkey());
//                            order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                            order.setUsersell("");
//                            order.setState(1);
//                            ordersMapper.insertSelective(order);
//                            int i =wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                            if(i > 0){
//                                trasferlistMapper.updateByfId(trasferlist.getFid());
//                            }
//                        }
//                    }else{
//                        System.out.println("发送矿工费失败,急需人工处理");
//                        trasferlistMapper.updateByfId2(trasferlist.getFid());
//                    }*/
//                    /*List<WellethistoryState> stateList = wellethistoryStateMapper.selectByWelletId(wellet.getFid(),1,1,1);
//                    if(stateList.size() <1){
//                        Date date = new Date();
//                        Income income = new Income();
//                        income.setType(8);
//                        income.setAmount(Double.valueOf(50));
//                        income.setRemark("注册成为有效用户");
//                        income.setUserId(Integer.valueOf(wellet.getUserkey()));
//                        income.setCreateTime(date);
//                        incomeMapper.insert(income);
//                        Recommend recommend = recommendService.selectByUserKey1(wellet.getUserkey());
//                        recommend.setIsNode(1);
//                        recommendService.updateByPrimaryKeySelective(recommend);
//                        Wellets wellets = welletsMapper.selectByUserandcoin(wellet.getUserkey(),2);
//                        wellets.setLocknum(BigDecimal.valueOf(wellets.getLocknum().doubleValue() + 50));
//                        welletsMapper.updateByPrimaryKeySelective(wellets);
//
//                        //添加EXC奖励记录
//                        AirdropReward airdropReward = new AirdropReward();
//                        airdropReward.setUserId(Integer.parseInt(wellet.getUserkey()));
//                        airdropReward.setCode(UUIDUtil.nextId());
//                        airdropReward.setStatus(1);
//                        airdropReward.setType(RewardEnum.REGISTER.getVal());
//                        airdropReward.setBonus(50d);
//                        airdropReward.setDailyRelease(ConfigConst.SPREAD_RELEASE);
//                        airdropReward.setTotal(0d);
//                        airdropReward.setCreateTime(date);
//                        airdropReward.setUpdateTime(date);
//                        airdropRewardMapper.insert(airdropReward);
//                    }*/
//                } else {
//                    trasferlistMapper.updateByfId3(trasferlist.getFid());
//                }
//            }
//        }
//    }
//
//
//    //@Scheduled(fixedRate = 1000 * 60,initialDelay = 3000)
//    public void BTCRecharge() {
//        List<Btctrasferlist> list = btctrasferlistMapper.selectBystate(0);
//        for (Btctrasferlist trasferlist : list) {
//            if (trasferlist.getMemo() != null && !"".equals(trasferlist.getMemo())) {
//                Wellets wellet = welletsMapper.selectByAddressAndCoin(trasferlist.getMemo(), 3);
//                if (wellet != null) {
//                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                    int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                    WellethistoryState wellethistoryState2 = new WellethistoryState();
//                    wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    wellethistoryState2.setType(1);
//                    wellethistoryState2.setRemark("充币");
//                    Date time = new Date();
//                    wellethistoryState2.setCreatetime(time);
//                    wellethistoryState2.setUpdatetime(time);
//                    wellethistoryState2.setWelletid(wellet.getFid());
//                    wellethistoryState2.setState(1);
//                    wellethistoryState2.setCointype(wellet.getCoinid());
//                    wellethistoryState2.setKeyes(trasferlist.getTxid());
//                    wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                    Orders order = new Orders();
//                    order.setOrderid(trasferlist.getTxid());
//                    order.setTradecoin(wellet.getCointype());
//                    order.setTradecoinid(wellet.getCoinid());
//                    order.setCreatetime(new Date());
//                    order.setConfirmtime(new Date());
//                    order.setBuyadress(trasferlist.getMemo());
//                    order.setUserbuy(wellet.getUserkey());
//                    order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    order.setUsersell("");
//                    order.setState(1);
//                    if (j > 0) {
//                        ordersMapper.insertSelective(order);
//                        int i = wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                        if (i > 0) {
//                            btctrasferlistMapper.updateByfId(trasferlist.getFid());
//                            System.out.println("汇总到主账号" + (trasferlist.getTradenum()));
//                            // String result = BTCUtilHuang.transaction(wellet.getAddress(),ConfigConst.BTCReceive_ADDRESS,BigDecimal.valueOf(trasferlist.getTradenum()));
//                            Orders order1 = new Orders();
//                            order1.setOrderid(trasferlist.getTxid());
//                            order1.setTradecoin("BTC");
//                            order1.setTradecoinid(3);
//                            order1.setCreatetime(new Date());
//                            order1.setConfirmtime(new Date());
//                            order1.setBuyadress(ConfigConst.BTCReceive_ADDRESS);
//                            order1.setUserbuy(String.valueOf(0000000));
//                            order1.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                            order1.setUsersell("000" + wellet.getUserkey());
//                            order1.setState(1);
//                            ordersMapper.insertSelective(order);
//                        }
//                    }
//                } else {
//                    btctrasferlistMapper.updateByfId3(trasferlist.getFid());
//                }
//            }
//        }
//    }
//
////    @Scheduled(fixedRate = 1000 * 60, initialDelay = 3000)
//    public void TokenRecharge() {
//        List<Trasferlist> list = trasferTokenlistMapper.selectBystate(0);
//        for (Trasferlist trasferlist : list) {
//            if (trasferlist.getMemo() != null && !"".equals(trasferlist.getMemo())) {
//                Wellets wellet = welletsMapper.selectByAddressAndCoin(trasferlist.getMemo(), trasferlist.getCoin());
//                if (wellet != null) {
//                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                    int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                    WellethistoryState wellethistoryState2 = new WellethistoryState();
//                    wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    wellethistoryState2.setType(1);
//                    wellethistoryState2.setRemark("充币");
//                    Date time = new Date();
//                    wellethistoryState2.setCreatetime(time);
//                    wellethistoryState2.setUpdatetime(time);
//                    wellethistoryState2.setWelletid(wellet.getFid());
//                    wellethistoryState2.setState(1);
//                    wellethistoryState2.setCointype(wellet.getCoinid());
//                    wellethistoryState2.setKeyes(trasferlist.getTxid());
//                    wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                    Orders order = new Orders();
//                    order.setOrderid(trasferlist.getTxid());
//                    order.setTradecoin(wellet.getCointype());
//                    order.setTradecoinid(wellet.getCoinid());
//                    order.setCreatetime(new Date());
//                    order.setConfirmtime(new Date());
//                    order.setBuyadress(trasferlist.getMemo());
//                    order.setUserbuy(wellet.getUserkey());
//                    order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                    order.setUsersell("");
//                    order.setState(1);
//                    ordersMapper.insertSelective(order);
//                    if (j > 0) {
//                        int i = wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                        if (i > 0) {
//                            trasferTokenlistMapper.updateByfId(trasferlist.getFid());
//                        }
//                    }
//                    /*long fee = (long) EthUtil.getGasPrice();
//                    System.out.println("汇总到主账号"+(trasferlist.getTradenum()-(fee*80000.0/1000000000000000000l)));
//                    String result = EthUtil.tokensend(wellet.getAddress(),ConfigConst.Receive_ADDRESS,trasferlist.getTradenum()-(fee*80000.0/1000000000000000000l),ConfigConst.Turn_PWD, fee);
//                    if(result == null || "null".equals(result)){
//                        System.out.println("汇总到主账号失败");
//                        trasferTokenlistMapper.updateByfId2(trasferlist.getFid());
//                    }else {
//                        wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                        int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                        WellethistoryState wellethistoryState2 = new WellethistoryState();
//                        wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                        wellethistoryState2.setType(1);
//                        wellethistoryState2.setRemark("充币");
//                        Date time = new Date();
//                        wellethistoryState2.setCreatetime(time);
//                        wellethistoryState2.setUpdatetime(time);
//                        wellethistoryState2.setWelletid(wellet.getFid());
//                        wellethistoryState2.setState(1);
//                        wellethistoryState2.setCointype(wellet.getCoinid());
//                        wellethistoryState2.setKeyes(trasferlist.getTxid());
//                        wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                        Orders order = new Orders();
//                        order.setOrderid(trasferlist.getTxid());
//                        order.setTradecoin(wellet.getCointype());
//                        order.setTradecoinid(wellet.getCoinid());
//                        order.setCreatetime(new Date());
//                        order.setConfirmtime(new Date());
//                        order.setBuyadress(trasferlist.getMemo());
//                        order.setUserbuy(wellet.getUserkey());
//                        order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                        order.setUsersell("");
//                        order.setState(1);
//                        ordersMapper.insertSelective(order);
//                        int i =wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                        if(i > 0){
//                            trasferTokenlistMapper.updateByfId(trasferlist.getFid());
//                        }*/
//                } else {
//                    trasferTokenlistMapper.updateByfId3(trasferlist.getFid());
//                }
//                    /*int g = transferfee2(wellet.getAddress(),fee);
//                    if(g > 0){
//                        System.out.println("汇总到主账号");
//                        String result = EthUtil.tokensend(wellet.getAddress(),ConfigConst.Receive_ADDRESS,trasferlist.getTradenum(),ConfigConst.Turn_PWD, fee);
//                        if(result == null || "null".equals(result)){
//                            System.out.println("汇总到主账号失败");
//                            trasferlistMapper.updateByfId2(trasferlist.getFid());
//                        }else {
//                            wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                            int j = welletsMapper.updateByPrimaryKeySelective(wellet);
//                            WellethistoryState wellethistoryState2 = new WellethistoryState();
//                            wellethistoryState2.setChangenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                            wellethistoryState2.setType(1);
//                            wellethistoryState2.setRemark("充币");
//                            Date time = new Date();
//                            wellethistoryState2.setCreatetime(time);
//                            wellethistoryState2.setUpdatetime(time);
//                            wellethistoryState2.setWelletid(wellet.getFid());
//                            wellethistoryState2.setState(1);
//                            wellethistoryState2.setCointype(wellet.getCoinid());
//                            wellethistoryState2.setKeyes(trasferlist.getTxid());
//                            wellethistoryState2.setUserid(Integer.valueOf(wellet.getUserkey()));
//                            Orders order = new Orders();
//                            order.setOrderid(trasferlist.getTxid());
//                            order.setTradecoin("EXC");
//                            order.setTradecoinid(2);
//                            order.setCreatetime(new Date());
//                            order.setConfirmtime(new Date());
//                            order.setBuyadress(trasferlist.getMemo());
//                            order.setUserbuy(wellet.getUserkey());
//                            order.setTradenum(BigDecimal.valueOf(trasferlist.getTradenum()));
//                            order.setUsersell("");
//                            order.setState(1);
//                            ordersMapper.insertSelective(order);
//                            int i =wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                            if(i > 0){
//                                trasferTokenlistMapper.updateByfId(trasferlist.getFid());
//                            }
//                        }
//                    }else{
//                        System.out.println("发送矿工费失败");
//                        trasferlistMapper.updateByfId2(trasferlist.getFid());
//                    }*/
//                    /*List<WellethistoryState> stateList = wellethistoryStateMapper.selectByWelletId(wellet.getFid(),1,1,1);
//                    if(stateList.size() <1){
//                        Income income = new Income();
//                        income.setType(8);
//                        income.setAmount(Double.valueOf(50));
//                        income.setRemark("注册成为有效用户");
//                        income.setUserId(Integer.valueOf(wellet.getUserkey()));
//                        income.setCreateTime(new Date());
//                        incomeMapper.insert(income);
//                    }*/
//            }
//        }
//    }
//
//
//    public void Transfer() {
//        List<Trasferlist> list = trasferlistMapper.selectByTransferState(0);
//        for (Trasferlist trasferlist : list) {
//            if (trasferlist.getMemo() != null && !"".equals(trasferlist.getMemo())) {
//                Wellets wellet = welletsMapper.selectByAddressAndCoin(trasferlist.getMemo(), 1);
//                if (wellet != null) {
//                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                    welletsMapper.updateByPrimaryKeySelective(wellet);
//                    WellethistoryState wellethistoryState2 = wellethistoryStateMapper.selectByKey(trasferlist.getTxid());
//                    wellethistoryState2.setState(1);
//                    int i = wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                    if (i > 0) {
//                        trasferlistMapper.updateByfId(trasferlist.getFid());
//                    }
//                }
//            }
//        }
//    }
//
//    public void TokenTransfer() {
//        List<Trasferlist> list = trasferTokenlistMapper.selectByTransferState(0);
//        for (Trasferlist trasferlist : list) {
//            if (trasferlist.getMemo() != null && !"".equals(trasferlist.getMemo())) {
//                Wellets wellet = welletsMapper.selectByAddressAndCoin(trasferlist.getMemo(), 2);
//                if (wellet != null) {
//                    wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + trasferlist.getTradenum()));
//                    welletsMapper.updateByPrimaryKeySelective(wellet);
//                    WellethistoryState wellethistoryState2 = wellethistoryStateMapper.selectByKey(trasferlist.getTxid());
//                    wellethistoryState2.setState(1);
//                    int i = wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                    if (i > 0) {
//                        trasferTokenlistMapper.updateByfId(trasferlist.getFid());
//                    }
//                }
//            }
//        }
//    }
//
//    public int transferfee(String address, long fee) {
//        String result = EthUtil.ethsend(ConfigConst.Turn_ADDRESS, address, fee * 21000 / 1000000000000000000l, ConfigConst.Turn_PWD, fee);
//        if (result == null || "null".equals(result)) {
//            return 0;
//        }
//        return 1;
//    }
//
//    public int transferfee2(String address, long fee) {
//        String result = EthUtil.ethsend(ConfigConst.Turn_ADDRESS, address, fee * 80000 / 1000000000000000000l, ConfigConst.Turn_PWD, fee);
//        if (result == null || "null".equals(result)) {
//            return 0;
//        }
//        return 1;
//    }
//
//    @Scheduled(cron = "0 0/1 * * * ?")
//    @RequestMapping("/sys/confirmtxid")
//    public void confirmtxid() {
//        /*if (!redisDao.hasKey("confirmtxid")){
//            return;
//        }*/
//        List<Map> processlist = ordersMapper.queryprocess();
//        if (processlist.size() > 0) {
//            for (Map map : processlist) {
//                String tx_hash = map.get("orderId") + "";
//                Orders orders = ordersMapper.selectByPrimaryKey(Integer.valueOf(map.get("fId") + ""));
//                if (orders != null) {
//                    boolean isconfirm = EthUtil.confirm(orders.getBuyadress());
//                    if (isconfirm) {
//                        try {
//                            List<WellethistoryState> wellethistoryStates = wellethistoryStateMapper.selectByKey2(tx_hash);
//                            WellethistoryState wellethistoryState1 = wellethistoryStates.get(0);
//                            WellethistoryState wellethistoryState2 = wellethistoryStates.get(1);
//                            wellethistoryState1.setState(1);
//                            wellethistoryState2.setState(1);
//                            wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState1);
//                            wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState2);
//                            orders.setState(1);
//                            ordersMapper.updateByPrimaryKeySelective(orders);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("错误：" + e.getMessage());
//                        }
//                        if (!redisDao.hasKey("withdraw:check1")) {
//                            redisDao.remove("withdraw:check1");
//                        }
//                    } else {
//                        if (orders.getProcesstime().getTime() + 1000 * 60 * 10 <= (new Date().getTime())) {
//                            System.out.println("错误：" + "txid:" + orders.getOrderid() + ",交易停滞，请注意查询是否失败！");
//                            orders.setProcessstate(3);
//                            ordersMapper.updateByPrimaryKeySelective(orders);
//                            SMSUtil.ypsendCode(parametersMapper.selectByKey("problemphone").getKeyvalue(), "0000");
//                            if (!redisDao.hasKey("withdraw:check1")) {
//                                redisDao.remove("withdraw:check1");
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            boolean withdraw = redisDao.hasKey("withdraw:check1");
//            if (!withdraw) {
//                List<Map> mapList = ordersMapper.querytxid();
//                for (Map map : mapList) {
//                    if (!redisDao.hasKey("withdraw:check1")) {
//                        try {
//                            redisDao.setKey1("withdraw:check1", "sure");
//                            String result = null;
//                            double fee = EthUtil.getGasPrice() * 1.2;
//                            Orders order = ordersMapper.selectByPrimaryKey(Integer.valueOf(map.get("fId") + ""));
//                            WithdrawLog withdrawLog = new WithdrawLog();
//                            withdrawLog.setNum(order.getTradenum());
//                            withdrawLog.setOrderId(order.getOrderid());
//                            withdrawLog.setSellAddress(order.getSelladress());
//                            withdrawLog.setSellUser(Integer.valueOf(order.getUsersell()));
//                            double ethbalance = EthUtil.getEthbalance(ConfigConst.Turn_ADDRESS);
//                            Wellets transfer = welletsMapper.selectByAddressAndCoin(order.getBuyadress(), order.getTradecoinid());
//                            if (transfer == null) {
//                                if (order.getTradecoinid() == 1) {
//                                    if (ethbalance < order.getTradenum().doubleValue() + fee * 21000.0 / 1000000000000000000l) {
//                                        System.out.println("错误：地址余额不足:" + ConfigConst.Turn_ADDRESS);
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    try {
//                                        // result = EthUtil.ethsend(ConfigConst.Turn_ADDRESS, order.getBuyadress(),order.getTradenum().doubleValue(), MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+"2012279"))), BigDecimal.valueOf(fee).longValue());
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                        System.out.println("错误：" + e.getMessage());
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
//                                    }
//                                } else if (order.getTradecoinid() == 3) {
//                                    if (BTCUtilHuang.getBtcBalance(ConfigConst.BTCTurn_ADDRESS) < order.getTradenum().doubleValue()) {
//                                        System.out.println("错误：地址余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    try {
//                                        // result = BTCUtilHuang.transaction(ConfigConst.BTCTurn_ADDRESS, order.getBuyadress(),order.getTradenum());
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                        System.out.println("错误：" + e.getMessage());
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
//                                    }
//                                }/*else if(order.getTradecoinid() == 5) {
//                                    if(ethbalance < fee*80000.0/1000000000000000000l ){
//                                        System.out.println("错误：手续费余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    double tokenbalance = EthUtil.getTokenbalance(ConfigConst.Turn_ADDRESS,ConfigConst.PLATFORM_ADDRESS);
//                                    if(tokenbalance < order.getTradenum().doubleValue() ){
//                                        System.out.println("错误：地址余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    try {
//                                        result = EthUtil.tokensend(ConfigConst.PLATFORM_ADDRESS,ConfigConst.Turn_ADDRESS, order.getBuyadress(),order.getTradenum().doubleValue(),ConfigConst.Turn_PWD, BigDecimal.valueOf(fee).longValue());
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                        System.out.println("错误："+e.getMessage());
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
//                                    }
//                                }*/ /*else if(order.getTradecoinid() == 6) {
//                                    if(ethbalance < fee*80000.0/1000000000000000000l ){
//                                        System.out.println("错误：手续费余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    double tokenbalance = EthUtil.getTokenbalance(ConfigConst.Turn_ADDRESS,ConfigConst.PLATFORM_ADDRESS1);
//                                    if(tokenbalance < order.getTradenum().doubleValue()){
//                                        System.out.println("错误：地址余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    try {
//                                        result = EthUtil.tokensend(ConfigConst.PLATFORM_ADDRESS1,ConfigConst.Turn_ADDRESS, order.getBuyadress(),order.getTradenum().doubleValue(),ConfigConst.Turn_PWD, BigDecimal.valueOf(fee).longValue());
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                        System.out.println("错误："+e.getMessage());
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
//                                    }
//                                }*/ /*else if(order.getTradecoinid() == 7) {
//                                    if(ethbalance < fee*80000.0/1000000000000000000l ){
//                                        System.out.println("错误：手续费余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    double tokenbalance = EthUtil.getTokenbalance(ConfigConst.Turn_ADDRESS,ConfigConst.ETPLATFORM_ADDRESS);
//                                    if(tokenbalance < order.getTradenum().doubleValue()){
//                                        System.out.println("错误：地址余额不足");
//                                        redisDao.remove("withdraw:check1");
//                                        break;
//                                        //SMSUtil.sendMsg(parametersMapper.selectByKey("problemphone").getKeyvalue(),"地址余额不足！");
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,"地址余额不足"));
//                                    }
//                                    try {
//                                        result = EthUtil.tokensend(ConfigConst.ETPLATFORM_ADDRESS,ConfigConst.Turn_ADDRESS, order.getBuyadress(),order.getTradenum().doubleValue(),ConfigConst.Turn_PWD, BigDecimal.valueOf(fee).longValue());
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                        System.out.println("错误："+e.getMessage());
//                                        //return JSONObject.toJSONString(new ResultDTO(-1,e.getMessage()));
//                                    }
//                                }*/
//                            } else {
//                                transfer.setCoinnum(BigDecimal.valueOf(transfer.getCoinnum().doubleValue() + order.getTradenum().doubleValue()));
//                                int i = welletsMapper.updateByPrimaryKeySelective(transfer);
//                                if (i > 0) {
//                                    Date time = new Date();
//                                    result = order.getOrderid();
//                                    String result1 = order.getOrderid() + time.getTime();
//                                    WellethistoryState wellethistoryState2 = new WellethistoryState();
//                                    wellethistoryState2.setChangenum(BigDecimal.valueOf(order.getTradenum().doubleValue()));
//                                    wellethistoryState2.setType(1);
//                                    wellethistoryState2.setRemark("充币");
//                                    wellethistoryState2.setCreatetime(time);
//                                    wellethistoryState2.setUpdatetime(time);
//                                    wellethistoryState2.setWelletid(transfer.getFid());
//                                    wellethistoryState2.setState(1);
//                                    wellethistoryState2.setCointype(transfer.getCoinid());
//                                    wellethistoryState2.setKeyes(result1);
//                                    wellethistoryState2.setUserid(Integer.valueOf(transfer.getUserkey()));
//                                    Orders order1 = new Orders();
//                                    order1.setOrderid(result1);
//                                    order1.setTradecoin(transfer.getCointype());
//                                    order1.setTradecoinid(transfer.getCoinid());
//                                    order1.setCreatetime(new Date());
//                                    order1.setConfirmtime(new Date());
//                                    order1.setBuyadress(transfer.getAddress());
//                                    order1.setUserbuy(transfer.getUserkey());
//                                    order1.setTradenum(BigDecimal.valueOf(order.getTradenum().doubleValue()));
//                                    order1.setUsersell("");
//                                    order1.setState(1);
//                                    ordersMapper.insertSelective(order1);
//                                    wellethistoryStateMapper.insertSelective(wellethistoryState2);
//                                }
//                            }
//                            Date time = new Date();
//                            withdrawLog.setTxId(result);
//                            withdrawLog.setCreateTime(time);
//                            withdrawLogMapper.insertSelective(withdrawLog);
//                            if (result == null || "null".equals(result)) {
//                                if (result == null) {
//                                    order.setProcessstate(2);
//                                } else if ("null".equals(result)) {
//                                    order.setProcessstate(4);
//                                }
//                                result = null;
//                                System.out.println("提币失败");
//                                //return JSONObject.toJSONString(new ResultDTO(-1,"提币审核失败"));
//                            } else {
//                                order.setProcessstate(1);
//                            }
//                            List<WellethistoryState> wellethistoryStates = wellethistoryStateMapper.selectByKey2(order.getOrderid());
//                            if (wellethistoryStates.size() > 1) {
//                                WellethistoryState wellethistoryState = wellethistoryStates.get(0);
//                                WellethistoryState wellethistoryState1 = wellethistoryStates.get(1);
//                                wellethistoryState.setKeyes(result);
//                                wellethistoryState.setUpdatetime(time);
//                                wellethistoryState1.setKeyes(result);
//                                wellethistoryState1.setUpdatetime(time);
//                                if (result == null || "null".equals(result) || transfer != null) {
//                                    wellethistoryState1.setState(1);
//                                    wellethistoryState.setState(1);
//                                    order.setState(1);
//                                }
//                                wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState);
//                                wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState1);
//                            }
//                            order.setOrderid(result);
//                            order.setProcesstime(time);
//                            int i = ordersMapper.updateByPrimaryKeySelective(order);
//                            //return JSONObject.toJSONString(new ResultDTO(200,"提币审核成功"));
//                            redisDao.remove("withdraw:check1");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            redisDao.remove("withdraw:check1");
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public int back(String txid) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String result = null;
//        try {
//            result = HttpUtil.sendGet("https://api.etherscan.io/api?module=transaction&action=gettxreceiptstatus&txhash=" + txid + "&apikey=YourApiKeyToken");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (result == null || "".equals(result)) {
//            return -2;
//        }
//        JSONObject json = JSON.parseObject(result);
//        JSONObject resultjson = json.getJSONObject("result");
//        if ("".equals(resultjson.getString("status"))) {
//            return -1;
//        }
//        return resultjson.getInteger("status");
//    }
//
//    @Scheduled(cron = "0 0 0/5 * * ? ")
//    @RequestMapping("/sys/backtxid")
//    public void backtxid() {
//        List<Map> mapList = ordersMapper.querytxid1();
//        for (Map map : mapList) {
//            String tx_hash = map.get("orderId") + "";
//            int isconfirm = back(tx_hash);
//            if (isconfirm == -1 || isconfirm == 0) {
//                uptorder(tx_hash);
//            } else if (isconfirm == 1) {
//                confirmtxids(tx_hash);
//            }
//            System.out.println(tx_hash + "------------------------:" + isconfirm);
//        }
//    }
//
//
//    @RequestMapping("/sys/uptorder")
//    public String uptorder(String txid) {
//        List<WellethistoryState> wellethistoryStates = wellethistoryStateMapper.selectByKey2(txid);
//        WellethistoryState wellethistoryState1 = wellethistoryStates.get(0);
//        WellethistoryState wellethistoryState2 = wellethistoryStates.get(1);
//        wellethistoryState1.setState(-1);
//        wellethistoryState2.setState(-1);
//        wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState1);
//        wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState2);
//        Orders orders = ordersMapper.queryorderId(txid);
//        orders.setState(2);
//        orders.setProcessstate(1);
//        ordersMapper.updateByPrimaryKeySelective(orders);
//        Wellets wellets = welletsMapper.selectByUserandcoin(orders.getUsersell(), orders.getTradecoinid());
//        if (orders.getTradecoinid() == 1) {
//            wellets.setCoinnum(BigDecimal.valueOf(wellets.getCoinnum().doubleValue() + orders.getTradenum().doubleValue() + orders.getFee().doubleValue() * 21000.0 / 1000000000000000000l));
//        } else {
//            wellets.setCoinnum(BigDecimal.valueOf(wellets.getCoinnum().doubleValue() + orders.getTradenum().doubleValue() + orders.getFee().doubleValue() * 80000.0 / 1000000000000000000l));
//        }
//        welletsMapper.updateByPrimaryKeySelective(wellets);
//        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
//    }
//
//    @RequestMapping("/sys/confirm")
//    public String confirmtxids(String txid) {
//        try {
//            Orders orders = ordersMapper.queryorderId(txid);
//            List<WellethistoryState> wellethistoryStates = wellethistoryStateMapper.selectByKey2(txid);
//            WellethistoryState wellethistoryState1 = wellethistoryStates.get(0);
//            WellethistoryState wellethistoryState2 = wellethistoryStates.get(1);
//            wellethistoryState1.setState(1);
//            wellethistoryState2.setState(1);
//            wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState1);
//            wellethistoryStateMapper.updateByPrimaryKeySelective(wellethistoryState2);
//            orders.setState(1);
//            orders.setProcessstate(1);
//            ordersMapper.updateByPrimaryKeySelective(orders);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JSONObject.toJSONString(new ResultDTO(-1, e.getMessage()));
//        }
//        return JSONObject.toJSONString(new ResultDTO(200, "ok"));
//    }
//
//    public void failconfirm() {
//
//    }
//
//
//}
