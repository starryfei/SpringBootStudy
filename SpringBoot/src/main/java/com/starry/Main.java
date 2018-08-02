package com.starry;

import com.starry.stroage.StorageProperties;
import com.starry.stroage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import com.starry.entity.Quote;
/**
 * 
 * @ClassName  Main   
 * @Description SpringBoot启动类  
 * @author yafei.qin 
 * @date 2018年4月4日 下午6:21:35  
 *   
 *  @EnableCaching:缓存注解
 *  @EnableScheduling：定时任务注解
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableWebSocket
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * BootCommandLineRunner在启动时可以删除和重新创建该文件夹
     * @param storageService
     * @return
     */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (String... args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }
}
