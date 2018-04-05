package com.starry.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.starry.aspect.LogAspect;
import com.starry.web.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class GreetingController {
    private static final String template = "hello,%s";
    private final AtomicLong counter = new AtomicLong();

    @LogAspect
    @RequestMapping("gretting")
    @ResponseBody
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template,
                name));
    }
    @RequestMapping("aa")
    @ResponseBody
    public String aa(
            @RequestParam(value = "name", defaultValue = "world") String name) {
        return name;
    }
}
