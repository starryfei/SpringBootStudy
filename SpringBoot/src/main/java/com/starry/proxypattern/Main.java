package com.starry.proxypattern;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
     //    静态代理
        SubInterface sub = new ProxyClass(new SubClass());
        sub.execute();
    //  JDK动态代理client
        SubInterface subInterface = (SubInterface) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{SubInterface.class},new JDKProxySubject(new SubClass()) );
        subInterface.execute();
        subInterface.delete();
    //   cglib动态代理      
        CGLibProxy cProxy = new CGLibProxy();
        //通过动态生成子类的方式创建代理类
        ServiceImpl service = (ServiceImpl) cProxy.getPrxoy(ServiceImpl.class);
        service.create();
        service.query();
    }
}
