package com.lanjing.otc.util;

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

    public static JSONObject sendUnderwriterMsg(String phone, int status) {
        JSONObject result = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("apikey", API_KEY);
        map.put("mobile", phone);
        //1审核通过，2审核拒绝
        if (status == 1) {
            map.put("text", "【BT应用】您的承兑商请求已通过，请关注APP相关功能");
        } else if (status == 2) {
            map.put("text", "【BT应用】你的承兑商请求未通过，详情请咨询客服");
        } else {
            return result;
        }
        try {
            result = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY, map.get("text"), map.get("mobile")));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return result;
        }
        logger.info(result.toJSONString());
        return result;
    }

    /**
     * 取消订单信息通知
     * @param phone
     * @param code
     * @return
     */
    public static  JSONObject cancelOrder(String phone,String code){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        map.put("text",String.format("【BT应用】您好，订单%s已取消。",code));
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 订单完成信息通知
     * @param phone
     * @param code
     * @return
     */
    public static  JSONObject orderComplete(String phone,String code){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        map.put("text",String.format("【BT应用】您好，订单%s已完成。",code));
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 订单已锁定信息通知
     * @param phone
     * @param code
     * @param type 1 买家，2卖家
     * @return
     */
    public static  JSONObject orderLocked(String phone,String code,Integer type){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您的买单%s已提交，请及时确认支付，30分钟内未处理，订单会自动取消。",code));
        }else{
            map.put("text",String.format("【BT应用】您有新的卖单%s，买家将在30分钟内支付，请及时关注订单动态。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 订单已支付信息通知
     * @param phone
     * @param code
     * @param type 1 买家，2卖家
     * @return
     */
    public static  JSONObject orderPaid(String phone,String code,Integer type){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s已确认支付，卖家将在24小时内处理，请及时关注订单动态。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s买家已确认支付，请及时处理订单，24小时未处理，订单将自动放行。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 订单已驳回信息通知
     * @param phone
     * @param code
     * @param type 1 买家，2卖家
     * @return
     */
    public static  JSONObject orderOverrule(String phone,String code,Integer type){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s的支付确认被驳回，请及时处理，24小时未处理，将自动取消订单。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s的支付已驳回，买家将在24小时内处理，请及时关注订单动态。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(API_KEY,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 订单申述信息通知
     * @param phone
     * @param code
     * @param type 1 买家，2卖家
     * @return
     */
    public static  JSONObject orderRepresentation(String phone,String code,Integer type){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",API_KEY);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s您已发起申述，工作人员会及时处理，请保持联系。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s买家已发起申述，工作人员会及时处理，请保持联系。",code));
        }
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
