package com.starry.proxypattern;
/**
 * 
 * @ClassName  ServiceImpl 被代理类  
 * @Description    
 * @author yafei.qin 
 *     
 */
public class ServiceImpl {
    public void create(){
        System.out.println("create() is runing");
    }
    public void query(){
        System.out.println("query() is running");
    }
}
