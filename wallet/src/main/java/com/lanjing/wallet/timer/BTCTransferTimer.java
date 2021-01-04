package com.lanjing.wallet.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.dao.OrdersMapper;
import com.lanjing.wallet.dao.ParametersMapper;
import com.lanjing.wallet.dao.WellethistoryStateMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.model.*;
import com.lanjing.wallet.util.BTCUtilHuang;
import org.springframework.data.annotation.Transient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2019/9/25 12:03
 * 定时请求比特币的交易数据
 */
//@Component
//@EnableScheduling
public class BTCTransferTimer {

    @Resource
    private ParametersMapper parametersMapper;

    @Resource
    private WellethistoryStateMapper wellethistoryStateMapper;

    @Resource
    private WelletsMapper welletsMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Scheduled(cron = "0/30 * * * * ?")
    //@Scheduled(cron = "0 0/3 * * * ?")
    @Transactional
    public void doSomeThing() {
        try {
            OutputStream out = new FileOutputStream("btc定时归集.txt", true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParametersWithBLOBs parameters = parametersMapper.selectByKey("btc_transfer_num");
            int num = Integer.parseInt(parameters.getKeyvalue());

            int currentBlockNumber =  BTCUtilHuang.getBlockNumber()-1; //取得上一个区块，这样会稳定很多
            if(currentBlockNumber==-1){
                return;
            }

            System.out.print("本地区块："+num+"\t");
            System.out.print("线上区块："+currentBlockNumber+"\t");
            if(num>currentBlockNumber){
                return;
            }
            String hash =   BTCUtilHuang.getBlockHash(num);
            JSONArray array = BTCUtilHuang.listenTransactions(hash);
            int size = array.size();
            System.out.println("区块交易："+size);
            out.write(("本地区块："+num+"\t"+"线上区块："+currentBlockNumber+"\t"+"区块交易："+size).getBytes());

            if (size > 0) {
                for (int i = 0; i < size; i++) {


                    JSONObject object = array.getJSONObject(i);
                    if(object.getString("address")==null){
                       continue;
                    }
                    Wellets wellets = welletsMapper.selectByAddressAndCoin(object.getString("address"), 3);
                    WellethistoryState state = wellethistoryStateMapper.selectByKey(object.getString("txid"));
                    if (wellets != null && state == null&&object.getString("action").equals("receive")) {
                        //保存交易记录
                        state = new WellethistoryState();
                        state.setBalance(object.getBigDecimal("amount"));
                        state.setChangenum(object.getBigDecimal("amount"));
                        state.setCointype(3);
                        state.setCreatetime(new Date());
                        state.setKeyes(object.getString("txid"));
                        state.setRecharge(wellets.getAddress());
                        state.setState(1);
                        state.setType(1);
                        state.setUpdatetime(new Date());
                        state.setWelletid(wellets.getFid());
                        state.setWithdraw(null);
                        state.setRemark("充值");
                        state.setUserid(Integer.parseInt(wellets.getUserkey()));
                        wellethistoryStateMapper.insertSelective(state);


                        //添加order信息 冗余数据

                        Orders order1 = new Orders();
                        order1.setOrderid(object.getString("txid"));
                        order1.setTradecoin("BTC");
                        order1.setTradecoinid(3);
                        order1.setCreatetime(new Date());
                        order1.setConfirmtime(new Date());
                        order1.setBuyadress(object.getString("address"));
                        order1.setUserbuy(wellets.getUserkey());
                        order1.setTradenum(object.getBigDecimal("amount"));
                        order1.setUsersell("");
                        order1.setState(1);
                        ordersMapper.insertSelective(order1);





                        //修改用户的余额
                        wellets.setCoinnum(wellets.getCoinnum().add( (object.getBigDecimal("amount"))));
                        welletsMapper.updateByPrimaryKeySelective(wellets);

                        //做归集，将用户的币转入到冷钱包中   0.00000546
                        double fee = BTCUtilHuang.getFee(object.getString("address"));
                        if(object.getDouble("amount")>fee+0.00002){
                            BigDecimal tr_amount = new BigDecimal( object.getDouble("amount")-(fee+0.00002)).setScale(8,BigDecimal.ROUND_UP);
                            String result = BTCUtilHuang.transaction(object.getString("address"), ConfigConst.BTCReceive_ADDRESS,tr_amount);
                            String now = sdf.format(new Date());
                            out.write(("\r\n" + result+":"+now+"\r\n").getBytes());
                        }else{
                            String now = sdf.format(new Date());
                            out.write(("\r\n没有发起归集，链上目前需要的手续费为:"+String.format("%.10f",fee)+"::::"+now+"\r\n").getBytes());

                        }



                    }

                }



            }
            //修改数据库的交易长度
            parameters.setKeyvalue(num+1 + "");
            parametersMapper.updateByPrimaryKeySelective(parameters);


            out.flush();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
            //0.00025393
        }
    }

}
