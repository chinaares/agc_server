package com.lanjing.goods;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @date 2019/10/21 1:28
 */
public class MainBusiListeners implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SpringContextUtil.setApplicationContext(event.getApplicationContext());

    }
}
