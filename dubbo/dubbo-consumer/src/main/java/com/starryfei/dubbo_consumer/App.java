package com.starryfei.dubbo_consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.starryfei.dubbo.DemoService;

/**
 * Hello world!
 *
 */
public class App {
    @SuppressWarnings("resource")
    public static void main( String[] args )  {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo_consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.hello("feiii"));
    }
}
