package com.lanjing.wallet.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.*;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.model.Coins;
import com.lanjing.wallet.model.Orders;
import com.lanjing.wallet.model.ParametersWithBLOBs;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.service.WelletService;
import com.lanjing.wallet.util.BTCUtilHuang;
import com.lanjing.wallet.util.EthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

@Service("WelletService")
public class WelletServiceImpl implements WelletService {

    @Resource
    private WelletsMapper welletsMapper;

    @Resource
    private CoinsMapper coinsMapper;

    @Resource
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private UsersMapper usersMapper;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    @Override
    public int deleteByPrimaryKey(Integer fid) {
        return 0;
    }

    @Override
    public int insert(Wellets record) {
        return 0;
    }

    @Override
    public int insertSelective(Wellets record) {
        return 0;
    }

    @Override
    public Wellets selectByPrimaryKey(Integer fid) {
        return welletsMapper.selectByPrimaryKey(fid);
    }

    @Override
    public Map<String, String> selectByUserKey(String userkey) {
        List<Wellets> list = welletsMapper.selectByUserKey(userkey);
        List<Coins> coins = coinsMapper.selectAll();
        Map<String, Coins> coinlist = new HashMap<>();
        for (Coins c : coins) {
            coinlist.put(c.getFid() + "", c);
        }


        double sum = 0;
        Map<String, String> map = new HashMap<>();
        if (list == null) {
            return map;
        }
        if (list.size() < 1) {
            return map;
        }
        map.put("UID", list.get(0).getAddress());
        for (Wellets wellet : list) {
            Coins c = coinlist.get(wellet.getCoinid() + "");
            double cost = wellet.getCoinnum().doubleValue() * c.getPrice();
            if (wellet.getCoinid() == 6) {
                cost = wellet.getCoinnum().doubleValue();
            }
            sum = sum + cost;


            map.put(c.getCoinshort(), wellet.getCoinnum() + "");  //余额


            map.put(c.getCoinshort() + "cost", cost + "");
            map.put(c.getCoinshort() + "Id", wellet.getFid() + "");
        }
        map.put("sum", sum + "");
        return map;
    }

    @Override
    public Wellets selectByUserKey(String userkey, Integer coin) {
        List<Wellets> list = welletsMapper.selectByUserKey(userkey);
        Wellets wellet = null;
        for (Wellets wellet1 : list) {
            if (wellet1.getCoinid() == coin) {
                wellet = wellet1;
            }
        }
        return wellet;
    }

    @Override
    public int updateByPrimaryKeySelective(Wellets record) {
        return welletsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Wellets record) {
        return welletsMapper.updateByPrimaryKey(record);
    }

    @Override
    public Wellets selectByUserandcoin(String keyes, int coinId) {
        return welletsMapper.selectByUserandcoin(keyes, coinId);
    }

    @Override
    @Transactional
    public int withdraw(String userkey, String pwd, int coin, double amount, double gas_price, String to) {
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey, coin);
        wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() - (amount + gas_price)));
        String orderid = null;
        if (coin == 1) {
            orderid = EthUtil.ethsend(ConfigConst.Turn_ADDRESS, to, amount, ConfigConst.Turn_PWD, (long) (gas_price / 21000 * 1000000000000000000l));
        } else if (coin == 5) {
            orderid = EthUtil.tokensend(ConfigConst.PLATFORM_ADDRESS, ConfigConst.Turn_ADDRESS, to, amount, ConfigConst.Turn_PWD, (long) (gas_price / 80000 * 1000000000000000000l));
        } else if (coin == 6) {
            orderid = EthUtil.tokensend(ConfigConst.PLATFORM_ADDRESS1, ConfigConst.Turn_ADDRESS, to, amount, ConfigConst.Turn_PWD, (long) (gas_price / 80000 * 1000000000000000000l));
        }
        //orderid = UUID.randomUUID().toString().replace("-","");
        if (orderid != null && !"null".equals(orderid)) {
            welletsMapper.updateByPrimaryKeySelective(wellet);
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(wellet.getFid());
            wellethistoryState.setChangenum(BigDecimal.valueOf(-gas_price));
            wellethistoryState.setBalance(wellet.getCoinnum());
            wellethistoryState.setType(0);
            wellethistoryState.setRemark("手续费");
            Date time = new Date();
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setState(1);
            wellethistoryState.setCointype(1);
            wellethistoryState.setUserid(Integer.valueOf(userkey));

            WellethistoryState wellethistoryState1 = new WellethistoryState();
            wellethistoryState1.setWelletid(wellet.getFid());
            wellethistoryState1.setChangenum(BigDecimal.valueOf(-amount));
            wellethistoryState1.setType(2);
            wellethistoryState1.setRemark("提币");
            wellethistoryState1.setCreatetime(time);
            wellethistoryState1.setUpdatetime(time);
            wellethistoryState1.setState(0);
            wellethistoryState1.setKeyes(orderid);
            wellethistoryState1.setCointype(wellet.getCoinid());
            wellethistoryState1.setUserid(Integer.valueOf(userkey));
            wellethistoryState1.setBalance(wellet.getCoinnum());
            wellethistoryStateMapper.insertSelective(wellethistoryState1);
            wellethistoryStateMapper.insertSelective(wellethistoryState);
            Orders order = new Orders();
            order.setOrderid(orderid);
            order.setTradecoin(coin + "");
            order.setTradecoinid(coin);
            order.setCreatetime(new Date());
            order.setConfirmtime(new Date());
            order.setBuyadress(to);
            order.setState(0);
            order.setUserbuy("");
            order.setTradenum(BigDecimal.valueOf(amount));
            order.setUsersell(userkey);
            order.setSelladress(wellet.getAddress());
            ordersMapper.insertSelective(order);
            return 1;
        }
        return 0;
    }


    @Override
    public void withdraw1(String userkey, String pwd, int coin, BigDecimal amount, BigDecimal gas, String to) {
        Date time = new Date();
        //内转没手续费
        //1、查询地址
        //查询对方
        Wellets wellets = welletsMapper.selectByAddressAndCoin(to, coin);
        if (wellets != null) {
            //我的
            Wellets walled = welletsMapper.selectByUserandcoin(userkey, coin);
            BigDecimal subtract = walled.getCoinnum().subtract(amount);
            walled.setCoinnum(subtract);
            String orderId = UUID.randomUUID().toString().replace("-", "");
            CheckEx.error(welletsMapper.updateByPrimaryKeySelective(walled) < 1);

            //内转
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(walled.getFid());
            wellethistoryState.setChangenum(amount.negate());
            wellethistoryState.setBalance(walled.getCoinnum());
            wellethistoryState.setType(2);
            wellethistoryState.setRemark("提币");
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setState(1);
            wellethistoryState.setKeyes(orderId);
            wellethistoryState.setCointype(walled.getCoinid());
            wellethistoryState.setUserid(Integer.valueOf(userkey));
            CheckEx.error(wellethistoryStateMapper.insertSelective(wellethistoryState) < 1);

            Orders order = new Orders();
            order.setOrderid(orderId);
            order.setTradecoin(coin + "");
            order.setTradecoinid(coin);
            order.setCreatetime(new Date());
            order.setConfirmtime(new Date());
            order.setBuyadress(to);
            order.setState(1);
            order.setUserbuy("");
            order.setTradenum(amount);
            order.setUsersell(userkey);
            order.setFee(BigDecimal.ZERO);
            order.setSelladress(ConfigConst.PLATFORM_MAIN_ADDRESS);// 提币从平台中心地址转出
            CheckEx.error(ordersMapper.insertSelective(order) < 1);
            BigDecimal add1 = wellets.getCoinnum().add(amount);
            wellets.setCoinnum(add1);

            //更新对方
            int i = welletsMapper.updateByPrimaryKeySelective(wellets);
            CheckEx.db(i, "内转失败");
            //更新自己
            welletsMapper.updateByPrimaryKeySelective(walled);

            WellethistoryState wellethistoryState2 = new WellethistoryState();
            wellethistoryState2.setCreatetime(new Date());
            wellethistoryState2.setCointype(coin);
            wellethistoryState2.setRemark("充值");
            wellethistoryState2.setUpdatetime(new Date());
            wellethistoryState2.setState(1);
            wellethistoryState2.setType(1);
            wellethistoryState2.setChangenum(amount);
            wellethistoryState2.setKeyes("");
            wellethistoryState2.setUserid(Integer.parseInt(wellets.getUserkey()));
            wellethistoryState2.setRecharge(wellets.getAddress());
            wellethistoryState2.setWithdraw(walled.getAddress());
            wellethistoryState2.setWelletid(wellets.getFid());
            wellethistoryStateMapper.insertSelective(wellethistoryState2);

            // 充值记录
            Orders order2 = new Orders();
            order2.setCreatetime(new Date());
            order2.setTradecoinid(coin);
            order2.setOrderid("");
            order2.setTradecoin(coin + "");
            order2.setUserbuy(wellets.getUserkey());
            order2.setBuyadress(wellets.getAddress());
            order2.setUsersell("");
            order2.setSelladress(walled.getAddress());
            order2.setFee(BigDecimal.ZERO);
            order2.setTradenum(amount);
            order2.setConfirmtime(new Date());
            order2.setProcesstime(new Date());
            order2.setState(1);
            order2.setProcessstate(0);
            ordersMapper.insert(order2);
        } else {
            //我的
            Wellets welled = welletsMapper.selectByUserandcoin(userkey, coin);
            BigDecimal add = amount.add(gas);
            BigDecimal subtract = welled.getCoinnum().subtract(add);
            welled.setCoinnum(subtract);
            String orderId = UUID.randomUUID().toString().replace("-", "");
            CheckEx.error(welletsMapper.updateByPrimaryKeySelective(welled) < 1);

            //统计今天最大的提币额度
            ParametersWithBLOBs parametersWithBLOBs = null;
            if (coin == 1) {//ETH
                parametersWithBLOBs = parametersService.findByKey("ethWithdrawLimit");
            } else if (coin == 2) {//AGC
                parametersWithBLOBs = parametersService.findByKey("agcWithdrawLimit");
            } else if (coin == 4) {//integral
                parametersWithBLOBs = parametersService.findByKey("integralWithdrawLimit");
            } else if (coin == 6) {//USDT
                parametersWithBLOBs = parametersService.findByKey("usdtWithdrawLimit");
            }
            if (parametersWithBLOBs != null) {
                BigDecimal limitNumber = new BigDecimal(parametersWithBLOBs.getKeyvalue());
                if (amount.compareTo(limitNumber) > 0) {//如果单笔就超多一天的最大限额
                    CheckEx.ex("提币额度超过最大限制");
                }

                //统计今天的额度
                List<WellethistoryState> wellethistoryStateList = wellethistoryStateMapper.selectNewStatistics(userkey, coin);
                BigDecimal changed = BigDecimal.ZERO;
                for (WellethistoryState wellethistoryState : wellethistoryStateList) {
                    changed = changed.add(wellethistoryState.getChangenum().negate());
                }

                changed = changed.add(amount);//今日提币的总额
                if (changed.compareTo(limitNumber) > 0) {
                    CheckEx.ex("提币额度超过最大限制");
                }
            }

            //场外
            WellethistoryState wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(welled.getFid());
            wellethistoryState.setChangenum(gas.negate());
            wellethistoryState.setBalance(welled.getCoinnum());
            wellethistoryState.setType(0);
            wellethistoryState.setRemark("手续费");
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setKeyes(orderId);
            wellethistoryState.setState(0);
            wellethistoryState.setCointype(welled.getCoinid());
            wellethistoryState.setUserid(Integer.valueOf(userkey));
            CheckEx.error(wellethistoryStateMapper.insertSelective(wellethistoryState) < 1);

            wellethistoryState = new WellethistoryState();
            wellethistoryState.setWelletid(welled.getFid());
            wellethistoryState.setChangenum(amount.negate());
            wellethistoryState.setBalance(welled.getCoinnum());
            wellethistoryState.setType(2);
            wellethistoryState.setRemark("提币");
            wellethistoryState.setCreatetime(time);
            wellethistoryState.setUpdatetime(time);
            wellethistoryState.setState(0);
            wellethistoryState.setKeyes(orderId);
            wellethistoryState.setCointype(welled.getCoinid());
            wellethistoryState.setUserid(Integer.valueOf(userkey));
            CheckEx.error(wellethistoryStateMapper.insertSelective(wellethistoryState) < 1);

            Orders order = new Orders();
            order.setOrderid(orderId);
            order.setTradecoin(coin + "");
            order.setTradecoinid(coin);
            order.setCreatetime(new Date());
            order.setConfirmtime(new Date());
            order.setBuyadress(to);
            order.setState(-1);
            order.setUserbuy("");
            order.setTradenum(amount);
            order.setUsersell(userkey);
            order.setFee(gas);
            order.setSelladress(ConfigConst.PLATFORM_MAIN_ADDRESS);// 提币从平台中心地址转出
            CheckEx.error(ordersMapper.insertSelective(order) < 1);
        }
    }

    @Override
    @Transactional
    public int transfer(int coin, String userkey, double num) {
        Wellets wellet = welletsMapper.selectByUserandcoin(userkey, coin);
        int i = 0;
        if (wellet.getReleasenum().doubleValue() <= 0) {
            return 0;
        }
        Date time = new Date();
        WellethistoryState wellethistoryState1 = new WellethistoryState();
        wellethistoryState1.setWelletid(wellet.getFid());
        wellethistoryState1.setChangenum(BigDecimal.valueOf(num));
        wellethistoryState1.setBalance(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + num));
        wellethistoryState1.setType(3);
        wellethistoryState1.setRemark("转入");
        wellethistoryState1.setCreatetime(time);
        wellethistoryState1.setUpdatetime(time);
        wellethistoryState1.setState(-1);
        wellethistoryState1.setCointype(wellet.getCoinid());
        wellethistoryState1.setUserid(Integer.valueOf(userkey));
        wellethistoryStateMapper.insertSelective(wellethistoryState1);
        //wellet.setCoinnum(BigDecimal.valueOf(wellet.getCoinnum().doubleValue() + num));
        wellet.setReleasenum(BigDecimal.valueOf(wellet.getReleasenum().doubleValue() - num));
        i = welletsMapper.updateByPrimaryKeySelective(wellet);
        return i;
    }

    @Override
    public int transfer(double num, Double amount, String userKey, Integer id, Integer version) {
        return welletsMapper.transfer(num, amount, userKey, id, version);
    }
}
