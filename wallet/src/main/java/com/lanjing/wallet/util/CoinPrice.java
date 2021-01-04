package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-18 15:45
 */
@SuppressWarnings("all")
public class CoinPrice {
    private static final String DZCC_CNYT = "https://api.exxvip.com/data/v1/ticker?currency=dzcc_cnyt";
    private static final String USDT_CNYT = "https://api.exxvip.com/data/v1/ticker?currency=usdt_cnyt";
    private static final String ET_CNYT = "https://api.exxvip.com/data/v1/ticker?currency=et_cnyt";

    public static JSONObject getPrice() {
        JSONObject result = new JSONObject();
        try {
            String content = HttpUtil.sendGet(USDT_CNYT);
            JSONObject info = JSONObject.parseObject(content);
            Double price = 1.0 / info.getJSONObject("ticker").getDouble("last");
            result.put("cnyt", price);

            content = HttpUtil.sendGet(DZCC_CNYT);
            info = JSONObject.parseObject(content);
            result.put("dzcc", info.getJSONObject("ticker").getDouble("last") * price);

            content = HttpUtil.sendGet(ET_CNYT);
            info = JSONObject.parseObject(content);
            result.put("et", info.getJSONObject("ticker").getDouble("last") * price);
            return result;
        } catch (Exception e) {
            return result;
        }
    }
}
