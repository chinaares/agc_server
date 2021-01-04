package com.lanjing.otc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jester.util.utils.Result;
import com.lanjing.otc.util.Des3Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@ControllerAdvice
public class ParamEncryptResponseBodyAdvice implements ResponseBodyAdvice {

    @Value("${encrypt.isopen}")
    private boolean isopen;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        URI uri = serverHttpRequest.getURI();
        if (uri.getPath().startsWith("/zuul")){
            return o;
        }
        if (isopen){
            JSONObject jsonObject = new JSONObject();
            String str = null;
            try {
                str = (String)o;
            }catch (Exception e){
                if (o instanceof Result) {
                    ObjectMapper mapper = new ObjectMapper();
                    DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    mapper.setDateFormat(df);

                    try {
                        str = mapper.writeValueAsString(o);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    str = JSONObject.toJSONStringWithDateFormat(o,"yyyy-MM-dd HH:mm:ss");
                }
            }
            System.out.println(str);
            String encodestr = Des3Util.encode(str);
            System.out.println(encodestr);
            jsonObject.put("data",encodestr);
            try {
                System.out.println(Des3Util.decode(encodestr));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(o.getClass().getName());
            if (o.getClass().getName().equals("java.lang.String")){
                return jsonObject.toJSONString();
            }
            return jsonObject;
        }
        return o;
    }
}
