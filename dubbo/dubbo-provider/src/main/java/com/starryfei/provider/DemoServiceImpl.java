package com.starryfei.provider;

import com.starryfei.dubbo.DemoService;
/**
 * 
 * @ClassName  DemoServiceImpl   
 * @Description dubbo service 的实现类 
 * @author yafei.qin 
 *
 */
public class DemoServiceImpl implements DemoService{

    public String hello(String name) {
        String result = String.format("hello %s", name);
        return result;
    }

}
