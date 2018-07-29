package com.starry.controller;

import com.starry.service.WetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName WhtherController
 * @Description TODO
 * @Author starryfei
 * @Date 2018/7/29 18:33
 * @Version 1.0
 **/
@Controller
public class WhtherController {
    @Autowired
    private WetherService wetherService;
    @PostMapping("/Weather")
    public String Weather(@RequestParam("cityName") String cityName) {
        return  wetherService.getWetherByName(cityName);
    }
}
