package com.starry.proxypattern;

/**
 * 目标类
 */
public class SubClass implements SubInterface {
    @Override
    public void execute() {
        System.out.println("real subject execute");
    }

    @Override
    public void delete() {
        System.out.println("real subject delete");
    }
}
