package com.lanjing.goods.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.goods.config.ConfigConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class BTCUtil {
    private static Logger logger = LoggerFactory.getLogger(EthUtil.class);

    /**
     * 获取地址
     *
     * @return
     */
    public static String getbtcAdress(){
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.BTCNODE_ADDRESS+"/btc/address","");
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String address = String.valueOf(map.get("address"));
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * eth转账
     *
     * @param from 发送地址
     * @param to 接收地址
     * @param amount 金额
     * @param gas_price gasPrice
     * @return
     */
    public static String transaction(String from, String to, BigDecimal amount, BigDecimal gas_price){
        JSONObject json = new JSONObject();
        json.put("from",from);
        json.put("to",to);
        json.put("amount",amount);
        json.put("gas_price",gas_price);
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.BTCNODE_ADDRESS+"/btc/transaction",json.toJSONString());
            System.out.println("back:"+backstr+",request:"+json.toJSONString());
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String tx_hash = String.valueOf(map.get("tx_id"));
            return tx_hash;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 确认交易
     *
     * @param tx_hash 交易标识
     * @return
     */
    public static int confirm(String tx_hash){
        try {
            String backstr = HttpUtil.sendGet(ConfigConst.BTCNODE_ADDRESS+"/btc/confirm/"+tx_hash);
            Map map = (Map) JSON.parseObject(backstr).get("data");
            int is_confirmed = Integer.parseInt(String.valueOf(map.get("is_confirmed")));
            return is_confirmed;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取btc余额
     *
     * @param address 地址
     * @return
     */
    public static BigDecimal getBtcbalance(String address){
        try {
            String backstr = HttpUtil.sendGet(ConfigConst.BTCNODE_ADDRESS+"/btc/balance/"+address);
            Map map = (Map) JSON.parseObject(backstr).get("data");
            double balance = Double.parseDouble(String.valueOf(map.get("balance")));
            return BigDecimal.valueOf(balance);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(0);
    }

    public static void main(String[] args) {
        System.out.println(getbtcAdress());
    }
}
