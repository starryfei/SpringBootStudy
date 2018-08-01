package com.starry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestConfiguration
 * @Description TODO
 * @Author starryfei
 * @Date 2018/7/29 18:26
 * @Version 1.0
 **/
public class RestConfiguration {
    @Autowired
    RestTemplateBuilder builder;
    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}
