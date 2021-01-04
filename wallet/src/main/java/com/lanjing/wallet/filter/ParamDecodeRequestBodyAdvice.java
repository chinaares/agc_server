package com.lanjing.wallet.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.util.Des3Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Map;

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
        HttpServletRequest request = getRequest();

        if(request.getRequestURI().contains("admin/uploadFileNotEncryption")){
            System.out.println(request.getRequestURI());
            return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
        }
        if(isopen && !request.getRequestURI().startsWith("/zuul")){
//            System.out.println(body);
            String decodestr = null;
            try {
                decodestr = Des3Util.decode(JSON.parseObject(JSONObject.toJSONString(body)).getString("data"));
                System.out.println(decodestr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object jsonbody = null;
            try {
                jsonbody = JSON.parseObject(decodestr,getClass().getClassLoader().loadClass(targetType.getTypeName()));
            } catch (ClassNotFoundException e) {
                Map<String,Object> json = JSON.parseObject(decodestr).getInnerMap();
                for (String key : json.keySet()){
                    json.put(key,json.get(key)+"");
                }
                jsonbody = json;
            }

            return super.afterBodyRead(jsonbody, inputMessage, parameter, targetType, converterType);
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    /**
     * try to get httpServletRequest from current thread holder
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =
                (HttpServletRequest)
                        requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        return request;
    }
}
