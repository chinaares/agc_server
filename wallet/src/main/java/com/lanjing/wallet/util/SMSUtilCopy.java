package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class SMSUtilCopy {

    private  static String ypsmsurl = "https://sms.yunpian.com/v2/sms/single_send.json";

    private  static String yunpiankey ="25f1cf0319b020c4fdc12a6932169ac8";

    public static  JSONObject ypsendCode(String phone,String code){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        map.put("text","【BT应用】您的验证码是"+code);
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static  JSONObject sendMsg(String phone,String context){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        map.put("text","【BT应用】"+context);
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * 取消订单信息通知
     * @param phone
     * @param code
     * @return
     */
    public static  JSONObject cancelOrder(String phone,String code){
        Map<String,String> map = new HashMap<>();
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        map.put("text",String.format("【BT应用】您好，订单%s已取消。",code));
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
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
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        map.put("text",String.format("【BT应用】您好，订单%s已完成。",code));
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
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
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您的买单%s已提交，请及时确认支付，30分钟内未处理，订单会自动取消。",code));
        }else{
            map.put("text",String.format("【BT应用】您有新的卖单%s，买家将在30分钟内支付，请及时关注订单动态。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
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
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s已确认支付，卖家将在24小时内处理，请及时关注订单动态。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s买家已确认支付，请及时处理订单，24小时未处理，订单将自动放行。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
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
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s的支付确认被驳回，请及时处理，24小时未处理，将自动取消订单。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s的支付已驳回，买家将在24小时内处理，请及时关注订单动态。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
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
        map.put("apikey",yunpiankey);
        map.put("mobile",phone);
        if (type==1){
            map.put("text",String.format("【BT应用】您好，订单%s您已发起申述，工作人员会及时处理，请保持联系。",code));
        }else{
            map.put("text",String.format("【BT应用】您好，订单%s买家已发起申述，工作人员会及时处理，请保持联系。",code));
        }
        JSONObject json;
        try {
            json = JSONObject.parseObject(JavaSmsApi.sendSms(yunpiankey,map.get("text"),map.get("mobile")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
