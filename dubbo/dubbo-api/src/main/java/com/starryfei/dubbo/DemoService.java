package com.starryfei.dubbo;
/**
 * 
 * @ClassName  DemoService   
 * @Description dubbo service api, 在consumer and provider中调用 
 * @author yafei.qin 
 *
 */
public interface DemoService {
    String hello(String name);
}
