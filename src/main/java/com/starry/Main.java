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

public class Main {
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
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
