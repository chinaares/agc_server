package com.lanjing.goods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.goods.util.Des3Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class ParamDecodeRequestBodyAdvice extends RequestBodyAdviceAdapter {
    @Value("${encrypt.isopen}")
    private boolean isopen;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if(isopen){
            String decodestr = null;
            try {
                decodestr = Des3Util.decode(JSON.parseObject(JSONObject.toJSONString(body)).getString("data"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object jsonbody = null;
            try {
                jsonbody = JSON.parseObject(decodestr,getClass().getClassLoader().loadClass(targetType.getTypeName()));
            } catch (ClassNotFoundException e) {
                jsonbody = JSON.parseObject(decodestr);
            }
            /*Map<String,Object> jsonmap = jsonbody.getInnerMap();
            for (String key:jsonmap.keySet()){
                jsonbody.put(key,jsonbody.get(key)+"");
            }
            jsonbody.putAll(jsonmap);*/
            return super.afterBodyRead(jsonbody, inputMessage, parameter, targetType, converterType);
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
