package com.lanjing.otc;

import com.lanjing.otc.util.GetInformation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@RestController
@MapperScan("com.lanjing.otc.dao")
@SpringBootApplication(scanBasePackages={"com.lanjing.otc","com.jester.util.utils"})
public class OtcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtcApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    @Bean
    public GetInformation getInformation(){
        return new GetInformation();
    }

}
