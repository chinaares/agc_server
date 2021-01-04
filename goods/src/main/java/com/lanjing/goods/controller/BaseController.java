package com.lanjing.goods.controller;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.goods.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public String getUserKey(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getString("keyes");
    }

    public Integer getId(){
        JSONObject admin = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return admin.getInteger("fId");
    }
}
