package com.lanjing.otc.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.otc.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public String getUserKey(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getString("keyes");
    }

    public Integer getUserId(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getInteger("keyes");
    }
}
