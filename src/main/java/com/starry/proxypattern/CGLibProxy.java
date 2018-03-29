package com.springboot.repository;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @ClassName CGLibProxy :代理类
 * @Description cglib 实现代理模式：创建目标类的子类
 * @author yafei.qin
 * 
 */
public class CGLibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    /**
     * 
     * @Description 创建目标类的子类的代理对象
     * @author yafei.qin
     * @param clz
     * @return Object
     */
    public Object getPrxoy(Class clz) {

        enhancer.setSuperclass(clz);// 设置需要创建的子类
        enhancer.setCallback(this);
        return enhancer.create();// 通过字节码技术动态创建子类实例
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg,
            MethodProxy proxy) throws Throwable {
        /*
         * 拦截父类所有的方法的调用 <br>obj:增强对象,<br>cglib创建的子类对象 method:<br>调用方法
         * proxy:调用父类方法的代理
         */
        System.out.println("开始执行 : " + method.getName() + "方法。。。。");
        System.out.println("实际调用者是： " + obj.getClass());//创建的子类
        Object result = proxy.invokeSuper(obj, arg);// 通过代理类调用父类中的方法
        System.out.println(method.getName() + "方法执行完毕！");
        return result;
    }

}
