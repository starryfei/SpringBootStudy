package com.starry.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * aspect
 */
public class JDKProxySubject implements InvocationHandler {
    private Object object;

    /**
     * @param object
     */
    public JDKProxySubject(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        result = method.invoke(object,args);
        return result;
    }
}
