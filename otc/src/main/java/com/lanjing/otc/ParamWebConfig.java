package com.lanjing.otc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ParamWebConfig implements WebMvcConfigurer {


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
    }

    /*@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
        super.extendMessageConverters(converters);
    }*/

    @Bean
    public SettingConverter messageConverter(){
        return new SettingConverter();
    }
}
