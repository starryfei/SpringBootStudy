package com.starry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
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
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
