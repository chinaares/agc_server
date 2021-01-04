package com.lanjing.goods.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SMSUtil {
    private static Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    private static String API_KEY = "25f1cf0319b020c4fdc12a6932169ac8";

    public static JSONObject sendMsg(String phone, String context) {
        Map<String, String> map = new HashMap<>();
        map.put("apikey", API_KEY);
        map.put("mobile", phone);
        map.put("text", "【BT应用】" + context);
        JSONObject result;
        try {
            result = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY, map.get("text"), map.get("mobile")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return result;
    }

    /**
     * 创建店铺信息通知
     * @param phone
     * @param password
     * @return
     */
    public static  JSONObject createStore(String phone,Integer password){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        map.put("text",String.format("【BT应用】尊敬的用户，您的店铺创建成功，密码%s；如有疑问请联系客服。",password));
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
