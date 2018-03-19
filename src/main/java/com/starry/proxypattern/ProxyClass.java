package com.starry.proxypattern;

/**
 * 代理类
 */
public class ProxyClass implements SubInterface {
    private SubClass subClass;

    ProxyClass(SubClass subClass){
        this.subClass = subClass;
    }
//    public void setSubClass(SubClass subClass) {
//        this.subClass = subClass;
//    }

    @Override
    public void execute() {
        System.out.println("before");
        this.subClass.execute();
        System.out.println("end");
    }

    @Override
    public void delete() {
        System.out.println("delete...");
        this.subClass.delete();
    }
}
