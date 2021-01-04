package com.lanjing.goods;

import com.lanjing.goods.dao.GoodsskuMapper;
import com.lanjing.goods.model.Goods;
import com.lanjing.goods.model.Goodssku;
import com.lanjing.goods.service.AdminService;
import com.lanjing.goods.service.GoodsService;
import com.lanjing.goods.util.GetInformation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@MapperScan("com.lanjing.goods.dao")
@SpringBootApplication(scanBasePackages = {"com.lanjing.goods", "com.jester.util.utils"})
public class GoodsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(GoodsApplication.class);
        sa.addListeners(new MainBusiListeners());
        sa.run(args);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    @Bean
    public GetInformation getInformation() {
        return new GetInformation();
    }


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsskuMapper goodsskuMapper;


    @Override
    public void run(String... args) throws Exception {
        List<Goods> goodsList = goodsService.selectAll();
        for (Goods good : goodsList) {
            List<Goodssku> goodsskus = goodsskuMapper.selectBygoodsCode(good.getCode() + "");
            if (goodsskus != null && goodsskus.size() > 0) {
                for (Goodssku goodssku : goodsskus) {
                    if (StringUtils.isEmpty(goodssku.getThumbnail())) {
                        goodssku.setThumbnail(good.getThumbnail());
                        goodsskuMapper.updateByPrimaryKeySelective(goodssku);
                    }
                }
            }
        }
    }
}
