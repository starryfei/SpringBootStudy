package com.starry.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class InfoEntity {
    @Value("${com.starry.demo.name}")
    private String name;
    @Value("${com.starry.demo.des}")
    private String des;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
