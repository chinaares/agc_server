package com.lanjing.wallet.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class SettingConverter extends AbstractHttpMessageConverter {
    @Value("${encrypt.isopen}")
    private boolean isopen;

    //Jackson的Json映射类
    private ObjectMapper mapper = new ObjectMapper();

    //该转换器的支持类型：application/json
    private List supportedMediaTypes = Arrays.asList(MediaType.APPLICATION_JSON);


    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        /*if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }*/
        return false;
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return supportedMediaTypes;
    }

    /*@Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Object obj = mapper.readValue(httpInputMessage.getBody(),aClass);
        return obj;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }*/

    /**
     * 定义字符编码，防止乱码
     */
    private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;

    /**
     * 新建自定义的媒体类型
     * */
    public SettingConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class aClass) {
        return true;
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(httpInputMessage.getBody(),JSONObject.class);
        if (!isopen){
            System.out.println(aClass.getName()+"-----------"+obj);
            obj = JSON.parseObject(JSON.toJSONString(obj),aClass);
        }
        return obj;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String result = null ;
        try {
            result = (String) o;
        }catch (Exception e){
            result = JSONObject.toJSONString(o);
        }
        httpOutputMessage.getBody().write(result.getBytes("utf-8"));
    }
}
