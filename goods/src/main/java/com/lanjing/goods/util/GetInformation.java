package com.lanjing.goods.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class GetInformation {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject getUser(String userkey){
        Object result = restTemplate.getForObject(urlStr+"zuul/getUser?userKey="+userkey,Object.class);
        JSONObject jsonObject = null;
        if (result.getClass().getName().equals("java.lang.String")){
            jsonObject = JSON.parseObject(String.valueOf(result));
        }else {
            jsonObject = JSON.parseObject(JSON.toJSONString(result));
        }
        return jsonObject;
    }

    public JSONObject getCoin(String coinId){
        Object result = restTemplate.getForObject(urlStr+"zuul/getcoin?fid="+coinId,Object.class);
        JSONObject jsonObject = null;
        if (result.getClass().getName().equals("java.lang.String")){
            jsonObject = JSON.parseObject(String.valueOf(result));
        }else {
            jsonObject = JSON.parseObject(JSON.toJSONString(result));
        }
        return jsonObject;
    }

    public JSONObject getCoincnyprice(String coinId){
        Object result = restTemplate.getForObject(urlStr+"zuul/getcoincnyprice?fid="+coinId,Object.class);
        JSONObject jsonObject = null;
        if (result.getClass().getName().equals("java.lang.String")){
            jsonObject = JSON.parseObject(String.valueOf(result));
        }else {
            jsonObject = JSON.parseObject(JSON.toJSONString(result));
        }
        return jsonObject;
    }

    public JSONObject postObject(String url,Object object){
        Object result = restTemplate.postForObject(url,object,Object.class);
        JSONObject jsonObject = null;
        if (result.getClass().getName().equals("java.lang.String")){
            jsonObject = JSON.parseObject(String.valueOf(result));
        }else {
            jsonObject = JSON.parseObject(JSON.toJSONString(result));
        }
        return jsonObject;
    }
}
