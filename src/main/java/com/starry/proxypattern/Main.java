package com.starry.proxypattern;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
//        静态代理
        SubInterface sub = new ProxyClass(new SubClass());
        sub.execute();
//  动态代理client
        SubInterface subInterface = (SubInterface) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{SubInterface.class},new JDKProxySubject(new SubClass()) );
        subInterface.execute();
        subInterface.delete();
    }
}
