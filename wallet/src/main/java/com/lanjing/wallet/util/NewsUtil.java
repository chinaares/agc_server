package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;

/**
 * 获取金色财经文章
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-09-02 10:45
 */
public class NewsUtil {
    private final static String SECRET_KEY = "18f6485568a8faeb";
    private final static String ACCESS_KEY = "b9fe1fc301d9bb6f7a30447d7b5f220b";
    private final static String BASE_URL = "http://api.coindog.com/topic/list";

    public static JSONArray getInfo() {
        long date = System.currentTimeMillis() / 1000;

        String signParam = String.format("access_key=%s&date=%d&secret_key=%s", ACCESS_KEY, date, SECRET_KEY);

        String sign = MD5Util.getMD5String(signParam).toLowerCase();

        String requestUrl = String.format("%s?access_key=b9fe1fc301d9bb6f7a30447d7b5f220b&date=%d&sign=%s", BASE_URL, date, sign);
        JSONArray result = null;
        try {
            String response = HttpUtil.sendGet(requestUrl);
            result = JSONArray.parseArray(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
