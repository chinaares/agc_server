package com.lanjing.zuul.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;


public class GetInformation {

    @Value("${wallet.url}")
    private String urlStr;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisDao redisDao;

    public JSONObject getUser(String userkey){
        String str = null ;
        try {
            str = redisDao.getValue("zuser"+userkey);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(str != null){
            return JSON.parseObject(str);
        }
        String result = restTemplate.getForObject(urlStr+"zuul/getUser?userKey="+userkey,String.class);
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject;
    }
}
