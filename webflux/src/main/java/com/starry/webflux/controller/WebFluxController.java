package com.starry.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.starry.webflux.bean.User;
import com.starry.webflux.dao.UserDao;
@Controller
public class WebFluxController {
    private UserDao userDao;
    @Autowired
    public WebFluxController(UserDao userDao) {
        this.userDao = userDao;
    }
    @ResponseBody
    @RequestMapping("/hello")
    public Mono<String> helloWorld(){
        return Mono.just("hello world");
    }
    @ResponseBody
    @RequestMapping("/alluser")
     public Flux<User> getAllUser(){
        return Flux.fromIterable(userDao.findAll());
    }
    @ResponseBody
    @RequestMapping(path="/alluser/{id}",method=RequestMethod.GET)
     public Mono<User> findUserById(@PathVariable("id")Long id){
        return Mono.justOrEmpty((userDao.findById(id)));
    }
}
