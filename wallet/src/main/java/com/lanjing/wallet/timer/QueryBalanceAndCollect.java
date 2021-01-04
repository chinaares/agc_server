package com.lanjing.wallet.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.agc.service.impl.AgcConsumeRecordServiceImpl;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.WellethistoryStateMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.ex.CheckEx;
import com.lanjing.wallet.model.Orders;
import com.lanjing.wallet.model.ParametersWithBLOBs;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.model.WellethistoryState;
import com.lanjing.wallet.model.Wellets;
import com.lanjing.wallet.service.OrdersService;
import com.lanjing.wallet.util.EthUtilHuang;
import com.lanjing.wallet.util.MD5Util;
import com.lanjing.wallet.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * @date 2019/9/19 18:15
 */
@Component
@EnableScheduling
public class QueryBalanceAndCollect {
	
	private static final Logger log = LoggerFactory.getLogger(QueryBalanceAndCollect.class);

    @Autowired
    private ParametersMapper parametersService;

    @Autowired
    private WelletsMapper welletService;

    @Autowired
    private WellethistoryStateMapper wellethistoryStateService;
    @Autowired
    private UsersMapper usersMapper;
    
    @Resource(name = "OrdersService")
    private OrdersService ordersService;

    @Scheduled(cron = "0 0/1 * * * ?")
    @RequestMapping("/doSomeThing")
    public void doSomeThing() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("查询区块：" + sdf.format(new Date()));
        try {
            OutputStream file = new FileOutputStream("query.txt", true);
            file.write(("\n查询区块：" + sdf.format(new Date())).getBytes());
            file.flush();
            file.close();
            ParametersWithBLOBs parameters = parametersService.selectByKey("eth_block");
            int ethBlock = EthUtilHuang.getBlock();
            if(ethBlock<=Integer.parseInt(parameters.getKeyvalue())){
                return;
            }
            if (!parameters.getKeyvalue().equals(ethBlock)) {
            	System.err.println("ETH节点区块：" + ethBlock+"扫描到区块："+parameters.getKeyvalue());
                //查询交易
                for (int m = Integer.parseInt(parameters.getKeyvalue()) + 1; m <= ethBlock; m++) {
                	System.err.println("扫描到区块："+m);
                    JSONArray array = EthUtilHuang.getHashListByBlock(m);
                    int size = array.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject object = array.getJSONObject(i);
                        String txid = object.getString("txid");
                        List wells = wellethistoryStateService.selectByKey2(txid);// 根据txid判断是否已经处理过
                        
                        if (wells == null || wells.size() == 0) {
                        	
                        	handleRecharge(object, txid);// 处理充值

                        }
                    }
                    parameters.setKeyvalue(m + "");
                    parametersService.updateByPrimaryKeySelective(parameters);
                }
                parameters.setKeyvalue(ethBlock + "");
                parametersService.updateByPrimaryKeySelective(parameters);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("区块结束：" + sdf.format(new Date()));
    }
    
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void handleRecharge(JSONObject object,String txid) {
    	
		List<Wellets> wellets = welletService.queryWelletByAdress(object.getString("to"));
        if (wellets != null && wellets.size() > 0) {//代表没有这条交易，需要存储
        	int confirm  = EthUtilHuang.confirm(txid);
        	if (confirm != 1) {// 不为1 未被确认
        		System.out.println("充值未被确认:"+object.toJSONString());
				return;
			}
        	
        	System.out.println("开始处理充值:"+object.toJSONString());
            if (!object.getBoolean("is_token") || object.getString("contract") == null) { //代表这个交易是ETH
                Wellets eth = welletService.selectByUserandcoin(wellets.get(0).getUserkey(), 1);
                Users users = usersMapper.selectByUserKey(eth.getUserkey());
                
                WellethistoryState wellethistoryState = new WellethistoryState();
                wellethistoryState.setCreatetime(new Date());
                wellethistoryState.setCointype(1);
                wellethistoryState.setRemark("充值");
                wellethistoryState.setUpdatetime(new Date());
                wellethistoryState.setState(1);
                wellethistoryState.setType(1);
                wellethistoryState.setChangenum(object.getBigDecimal("value"));
                wellethistoryState.setKeyes(txid);
                wellethistoryState.setUserid(Integer.parseInt(wellets.get(0).getUserkey()));
                wellethistoryState.setRecharge(object.getString("to"));
                wellethistoryState.setWithdraw(object.getString("from"));
                wellethistoryState.setWelletid(eth.getFid());
                wellethistoryStateService.insertSelective(wellethistoryState);
                
                // 充值记录
                Orders order = new Orders();
                order.setCreatetime(new Date());
                order.setTradecoinid(1);
                order.setOrderid(txid);
                order.setTradecoin("ETH");
                order.setUserbuy(wellets.get(0).getUserkey());
                order.setBuyadress(object.getString("to"));
                order.setUsersell("");
                order.setSelladress(object.getString("from"));
                order.setFee(BigDecimal.ZERO);
                order.setTradenum(object.getBigDecimal("value"));
                order.setConfirmtime(new Date());
                order.setProcesstime(new Date());
                order.setState(1);
                order.setProcessstate(0);
                ordersService.insert(order);
                
                eth.setCoinnum(eth.getCoinnum().add(object.getBigDecimal("value")));
                welletService.updateByPrimaryKey(eth);
                log.info("得到一条ETH的充值记录：[{}],{}",wellets.get(0).getUserkey() , object);

                //把钱归集到imtoken的钱包中 0x321Cb8273b236bfF143077150C870776e581e822 是AGC的冷钱包
                if (object.getDouble("value") > 0.001) {
                    String amount = object.getDouble("value") - 0.001 + "";
                    EthUtilHuang.ethTransfer(object.getString("to"), "0x321Cb8273b236bfF143077150C870776e581e822", users.getTranspassword(), amount, null);
                }


            } else if (object.getString("contract").equalsIgnoreCase(ConfigConst.PLATFORM_ADDRESS.toUpperCase())) {// AGC充值
                Wellets agc = welletService.selectByUserandcoin(wellets.get(0).getUserkey(), 2);
                WellethistoryState wellethistoryState = new WellethistoryState();
                wellethistoryState.setCreatetime(new Date());
                wellethistoryState.setCointype(2);
                wellethistoryState.setRemark("充值");
                wellethistoryState.setUpdatetime(new Date());
                wellethistoryState.setState(1);
                wellethistoryState.setType(1);
                wellethistoryState.setChangenum(object.getBigDecimal("value"));
                wellethistoryState.setKeyes(txid);
                wellethistoryState.setUserid(Integer.parseInt(wellets.get(0).getUserkey()));
                wellethistoryState.setRecharge(object.getString("to"));
                wellethistoryState.setWithdraw(object.getString("from"));
                wellethistoryState.setWelletid(agc.getFid());
                wellethistoryStateService.insertSelective(wellethistoryState);
                
                // 充值记录
                Orders order = new Orders();
                order.setCreatetime(new Date());
                order.setTradecoinid(2);
                order.setOrderid(txid);
                order.setTradecoin("AGC");
                order.setUserbuy(wellets.get(0).getUserkey());
                order.setBuyadress(object.getString("to"));
                order.setUsersell("");
                order.setSelladress(object.getString("from"));
                order.setFee(BigDecimal.ZERO);
                order.setTradenum(object.getBigDecimal("value"));
                order.setConfirmtime(new Date());
                order.setProcesstime(new Date());
                order.setState(1);
                order.setProcessstate(0);
                ordersService.insert(order);
                
                agc.setCoinnum(agc.getCoinnum().add(object.getBigDecimal("value")));
                welletService.updateByPrimaryKey(agc);
                log.info("得到一条Agc的充值记录：[{}],{}",wellets.get(0).getUserkey() , object);
                
                //把钱归集到imtoken的钱包中 0x321Cb8273b236bfF143077150C870776e581e822 是AGC的冷钱包
                if (object.getDouble("value") > 10) {
                    String amount = object.getString("value");
                    String password = MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+"2012457")));
                    String orderid = "";
                    try {
                    	// 发送ETH手续费
                    	EthUtilHuang.ethTransfer(ConfigConst.PLATFORM_MAIN_ADDRESS, object.getString("to"), password, "0.001", null);
                    	// 归集
                        orderid = EthUtilHuang.tokenTransfer(ConfigConst.PLATFORM_ADDRESS,object.getString("to"), "0x321Cb8273b236bfF143077150C870776e581e822",
                        		password,amount, 0.001);
                        if (StringUtils.isEmpty(orderid)) {
                        	System.out.println("归集失败！！"+object.getString("to"));
                            CheckEx.ex();
                        }
                        System.out.println("开始归集："+orderid);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CheckEx.ex();
                    }
                }
                
                
            } else if (object.getString("contract").equalsIgnoreCase(ConfigConst.PLATFORM_ADDRESS1)) {// Erc20-usdt充值
                Wellets usdt = welletService.selectByUserandcoin(wellets.get(0).getUserkey(), 6);
                WellethistoryState wellethistoryState = new WellethistoryState();
                wellethistoryState.setCreatetime(new Date());
                wellethistoryState.setCointype(6);
                wellethistoryState.setRemark("充值");
                wellethistoryState.setUpdatetime(new Date());
                wellethistoryState.setState(1);
                wellethistoryState.setType(1);
                wellethistoryState.setChangenum(object.getBigDecimal("value"));
                wellethistoryState.setKeyes(txid);
                wellethistoryState.setUserid(Integer.parseInt(wellets.get(0).getUserkey()));
                wellethistoryState.setRecharge(object.getString("to"));
                wellethistoryState.setWithdraw(object.getString("from"));
                wellethistoryState.setWelletid(usdt.getFid());
                wellethistoryStateService.insertSelective(wellethistoryState);
                
                // 充值记录
                Orders order = new Orders();
                order.setCreatetime(new Date());
                order.setTradecoinid(6);
                order.setOrderid(txid);
                order.setTradecoin("USDT");
                order.setUserbuy(wellets.get(0).getUserkey());
                order.setBuyadress(object.getString("to"));
                order.setUsersell("");
                order.setSelladress(object.getString("from"));
                order.setFee(BigDecimal.ZERO);
                order.setTradenum(object.getBigDecimal("value"));
                order.setConfirmtime(new Date());
                order.setProcesstime(new Date());
                order.setState(1);
                order.setProcessstate(0);
                ordersService.insert(order);
                
                usdt.setCoinnum(usdt.getCoinnum().add(object.getBigDecimal("value")));
                welletService.updateByPrimaryKey(usdt);
                log.info("得到一条USDT的充值记录：[{}],{}",wellets.get(0).getUserkey() , object);
                
                //把钱归集到imtoken的钱包中 0x321Cb8273b236bfF143077150C870776e581e822 是AGC的冷钱包
                if (object.getDouble("value") > 1) {
                    String amount = object.getString("value");
                    String password = MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+"2012457")));
                    String orderid = "";
                    try {
                    	// 发送ETH手续费
                    	EthUtilHuang.ethTransfer(ConfigConst.PLATFORM_MAIN_ADDRESS, object.getString("to"), password, "0.001", null);
                    	// 归集
                        orderid = EthUtilHuang.tokenTransfer(ConfigConst.PLATFORM_ADDRESS1,object.getString("to"), "0x321Cb8273b236bfF143077150C870776e581e822",
                        		password,amount, 0.001);
                        if (StringUtils.isEmpty(orderid)) {
                        	System.out.println("归集失败！！"+object.getString("to"));
                            CheckEx.ex();
                        }
                        System.out.println("开始归集："+orderid);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CheckEx.ex();
                    }
                }
            }

        }
    	
	}
    
    
}
