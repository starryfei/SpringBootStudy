package com.starry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ServletComponentScan
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
