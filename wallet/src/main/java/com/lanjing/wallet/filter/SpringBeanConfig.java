package com.lanjing.wallet.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class SpringBeanConfig {

    @Bean("createCoinAddressService")
    public ExecutorService createCoinAddressService() {
        return Executors.newFixedThreadPool(1);
    }
}
