package com.starry.web;

import com.starry.controller.SampleController;
import com.starry.entity.InfoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
public class HelloWorldControllerTest {
    @Autowired
    private InfoEntity info1;

    public void setInfo1(InfoEntity info1) {
        this.info1 = info1;
    }

    public InfoEntity getInfo1() {
        return info1;
    }

    @Test
    public void testSayHello(){
        assertEquals("Hello world",new SampleController().sayHello());
    }
    @Test
    public void testInfo() throws Exception{
        System.out.print(info1.getName());
        Assert.assertEquals("starry",info1.getName());
        Assert.assertEquals("spring-boot study",info1.getDes());
    }

}
