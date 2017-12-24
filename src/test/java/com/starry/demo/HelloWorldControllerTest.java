package com.starry.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldControllerTest {
    @Test
    public void testSayHello(){
        assertEquals("Hello world",new SampleController().sayHello());
    }
}
