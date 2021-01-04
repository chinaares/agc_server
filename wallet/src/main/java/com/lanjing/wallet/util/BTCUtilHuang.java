package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2019/9/23 14:08
 */
public class BTCUtilHuang {

    private static final String BTCSERVER = "http://47.112.125.161:9005/wallet/";


    /**
     * 获取地址
     *
     * @return
     */
    public static String getBtcAddress(String name) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            String backstr = HttpUtil.sendPost(BTCSERVER + "btc/getAddress", jsonObject.toJSONString());
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String address = String.valueOf(map.get("address"));
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * btc转账
     *
     * @param from   发送地址
     * @param to     接收地址
     * @param amount 金额
     * @return
     */
    public static String transaction(String from, String to, BigDecimal amount) {
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("amount", amount);
        // json.put("gas_price",gas_price);
        try {
            System.out.println(json.toJSONString());

            String data = AESEncrypt.encrypt(json.toJSONString(), "Sy5DE7F1IA2S105C");
            json.clear();
            json.put("enData", data);
            String backstr = HttpUtil.sendPost(BTCSERVER + "/btc/sendBtc", json.toJSONString());

            System.err.println("request:" + backstr);
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String tx_hash = String.valueOf(map.get("txHash"));
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
    public static boolean confirm(String tx_hash) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("txHash", tx_hash);
            String backstr = HttpUtil.sendPost(BTCSERVER + "/btc/confirmedTx", JSONObject.toJSONString(map));
            JSONObject jsonObject = JSONObject.parseObject(backstr);
            return jsonObject.getJSONObject("data").getBoolean("isConfirm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取btc余额
     *
     * @param address 地址
     * @return
     */
    public static double getBtcBalance(String address) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("address", address);
            String backstr = HttpUtil.sendPost(BTCSERVER + "/btc/getBalance", JSONObject.toJSONString(map));
            JSONObject object = JSONObject.parseObject(backstr);
            return object.getJSONObject("data").getDouble("balance");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * 监听btc的最近交易
     *
     * @param
     * @return
     */
    public static JSONArray listenTransactions(int count, int skip) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("count", count);
            map.put("skip", skip);
            String backstr = HttpUtil.sendPost(BTCSERVER + "/btc/listenTransactions", JSONObject.toJSONString(map));
            JSONObject object = JSONObject.parseObject(backstr);
            return object.getJSONObject("data").getJSONArray("transactionInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当前区块高度
     *
     * @param
     */
    public static int getBlockNumber() {
        try {
            Map<String, Object> map = new HashMap<>();
            String data = JSONObject.toJSONString(map);

            String result = HttpUtil.sendPost("http://47.112.125.161:9005/wallet/btc/getBlockNumber", data);
            JSONObject object = JSONObject.parseObject(result);
            return  object.getJSONObject("data").getInteger("current blockNumber");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * 通过区块高度，获取区块hash
     *
     * @param
     */
    public static String getBlockHash(int blockNumber) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("blockNumber",blockNumber);
            String data = JSONObject.toJSONString(map);

            String result = HttpUtil.sendPost("http://47.112.125.161:9005/wallet/btc/getBlockHash", data);
            JSONObject object = JSONObject.parseObject(result);
            return  object.getJSONObject("data").getString("blockHash");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过区块hash，查询交易详情
     *
     * @param
     */
    public static JSONArray listenTransactions(String hash) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("blockHash",hash);
            String data = JSONObject.toJSONString(map);
            String result = HttpUtil.sendPost("http://154.92.14.117:9005/wallet/btc/listenTransactions", data);
            JSONObject object = JSONObject.parseObject(result);
            return  object.getJSONObject("data").getJSONArray("transactionInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过区块hash，查询交易详情
     *
     * @param
     */
    public static Double getFee(String address) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("address",address);
            String data = JSONObject.toJSONString(map);
            String result = HttpUtil.sendPost("http://154.92.14.117:9005/wallet/btc/getFee", data);
            JSONObject object = JSONObject.parseObject(result);
            return  object.getJSONObject("data").getDouble("fee");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] arg0) {
        BigDecimal tr_amount = new BigDecimal( 0.001 -0.00002).setScale(8,BigDecimal.ROUND_UP);
        String result = transaction("1JTWnSqxE59Tsu91DvhJUe68MU126af3BX",ConfigConst.BTCReceive_ADDRESS,tr_amount);
        System.out.println(result);
    }

}
