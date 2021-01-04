package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;
import com.lanjing.wallet.model.EthApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

public class EthUtil {
    private static Logger logger = LoggerFactory.getLogger(EthUtil.class);

    /**
     * 获取地址
     *
     * @param pwd 交易密码
     * @return
     */
    public static String getethAdress(String pwd) {
        JSONObject json = new JSONObject();
        json.put("pwd", pwd);
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.NODE_ADDRESS + "/eth/address", json.toJSONString());
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
     * @param from      发送地址
     * @param to        接收地址
     * @param amount    金额
     * @param pwd       交易密码
     * @param gas_price gasPrice
     * @return
     */
    public static String ethsend(String from, String to, double amount, String pwd, long gas_price) {
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("amount", amount);
        json.put("pwd", pwd);
        json.put("gas_price", gas_price);
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.NODE_ADDRESS + "/eth/send", json.toJSONString());
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String tx_hash = String.valueOf(map.get("tx_hash"));
            return tx_hash;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * eth转账
     *
     * @param from      发送地址
     * @param to        接收地址
     * @param amount    金额
     * @param pwd       交易密码
     * @param gas_price gasPrice
     * @return
     */
    public static String ethSend(String from, String to, double amount, String pwd, long gas_price) {
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        json.put("amount", amount);
        json.put("pwd", pwd);
        json.put("gas_price", gas_price);
        try {
            String context = HttpUtil.sendPost(ConfigConst.NODE_ADDRESS + "/eth/send", json.toJSONString());
            logger.info(String.format("理财节点请求响应:%s,参数:%s", context, json));
            return JSON.parseObject(context).getString("code");
        } catch (IOException e) {
            logger.info(String.format("理财节点请求响应:%s", e.getMessage()));
            return "error";
        }
    }

    /**
     * 代币转账
     *
     * @param from      发送地址
     * @param to        接收地址
     * @param amount    金额
     * @param pwd       交易密码
     * @param gas_price gasPrice
     * @return
     */
    public static String tokensend(String adress, String from, String to, double amount, String pwd, double gas_price) {
        JSONObject json = new JSONObject();
        json.put("token_address", adress);
        json.put("from", from);
        json.put("to", to);
        json.put("amount", amount);
        json.put("pwd", pwd);
        json.put("gas_price", gas_price);
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.NODE_ADDRESS + "/token/send", json.toJSONString());
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String tx_hash = String.valueOf(map.get("tx_hash"));
            return tx_hash;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取eth余额
     *
     * @param address 地址
     * @return
     */
    public static double getEthbalance(String address) {
        try {
            String backstr = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/eth/balance/" + address);
            Map map = (Map) JSON.parseObject(backstr).get("data");
            double balance = Double.parseDouble(String.valueOf(map.get("balance")));
            return balance;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取代币余额
     *
     * @param address 地址
     * @return
     */
    public static double getTokenbalance(String address, String addr) {
        try {
            String backstr = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/token/balance/" + addr + "/" + address);
            Map map = (Map) JSON.parseObject(backstr).get("data");
            double balance = Double.parseDouble(String.valueOf(map.get("balance")));
            return balance;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 确认交易
     *
     * @param tx_hash 交易标识
     * @return
     */
    public static boolean confirm(String tx_hash) {
        try {
            String backstr = HttpUtil.sendGet("http://154.92.14.117:8085/eth/transaction?txid=" + tx_hash);
            if (!StringUtils.isEmpty(backstr)) {
                Map map = (Map) JSON.parseObject(backstr).get("data");
                if (map != null && map.containsKey("status")) {
                    String status = (String) map.get("status");
                    if (!StringUtils.isEmpty(status)) {
                        return status.equals("confirm");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取GasPrice
     *
     * @return
     */
    public static double getGasPrice() {
        try {
            String backstr = HttpUtil.sendGet(ConfigConst.NODE_ADDRESS + "/eth/gasPrice");
            Map map = (Map) JSON.parseObject(backstr).get("data");
            double gas_price = Double.parseDouble(String.valueOf(map.get("gas_price")));
            return gas_price;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取GasPrice
     *
     * @return
     */
    public static EthApiModel getGasPrice2() {
        EthApiModel ethApiModel = new EthApiModel();
        try {
            String backstr = HttpUtil.sendGet("https://ethgasstation.info/json/ethgasAPI.json");
            JSONObject jsonObject = JSON.parseObject(backstr);
            ethApiModel.setFast(jsonObject.getBigDecimal("fast"));
            ethApiModel.setFastest(jsonObject.getBigDecimal("fastest"));
            ethApiModel.setSafeLow(jsonObject.getBigDecimal("safeLow"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ethApiModel;
    }

    /**
     * 导入钱包
     *
     * @param key 私钥
     * @param pwd 交易密码
     * @return
     */
    public static boolean importWellet(String key, String pwd) {
        JSONObject json = new JSONObject();
        json.put("pk", key);
        json.put("pwd", pwd);
        try {
            String backstr = HttpUtil.sendPost(ConfigConst.NODE_ADDRESS + "/eth/import", json.toJSONString());
            Map map = (Map) JSON.parseObject(backstr).get("data");
            String is_succeed = String.valueOf(map.get("is_succeed"));
            if ("true".equals(is_succeed)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        String str = ethsend("0x65A3772F0A22dB5BB691b2E223aB2c5444dDe88a", "0x88d1518da81d34dde9a193ef94f736c2e3328837", 0.000001, "SADAFAGAnvxvf236546$#%", (long) (0.0000001 / 21000 * 1000000000000000000l));
        //boolean ok =    importWellet("942c282463fc71ca70e9d6f90ad3663eb911b2d22ea4b382735ad6ab58654b3d","SADAFAGAnvxvf236546$#%");
        System.out.println(str);
    }

}
