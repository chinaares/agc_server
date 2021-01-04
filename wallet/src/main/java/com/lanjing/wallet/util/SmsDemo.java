package com.lanjing.wallet.util;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class SmsDemo {

    static String urlPath = "http://api.liasmart.com/api/SendSMS";

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("api_id", "API3649882086");
        params.put("api_password", "Hg7PpD4TRZ");
        params.put("sms_type", "T");
        params.put("encoding", "U");
        params.put("sender_id", "LIASMT");
        params.put("phonenumber", "8618370853246");
        params.put("textmessage", "【AGC】您的验证码是123456。如非本人操作，请忽略本短信");
        String resultString = null;
        try {
            resultString = post(urlPath, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultString);
    }

    public static boolean mobileQuery(String mobile, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("api_id", "API3649882086");
        params.put("api_password", "Hg7PpD4TRZ");
        params.put("sms_type", "T");
        params.put("encoding", "U");
        params.put("sender_id", "LIASMT");
        params.put("phonenumber", "86" + mobile);
        params.put("textmessage", "【AGC】您的验证码是" + code + "。如非本人操作，请忽略本短信");
        String resultString = null;
        try {
            resultString = post(urlPath, params);
            JSONObject object = JSONObject.fromObject(resultString);
            long message_id = object.getLong("message_id");
            if (message_id > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String post(String strUrl, Map<String, String> params) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(com.alibaba.fastjson.JSON.toJSONString(params).getBytes());
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
} 