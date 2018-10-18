package com.starryfei.elastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starryfei.elastic.bean.User;
import com.starryfei.elastic.dao.UserDao;
import com.starryfei.elastic.mapper.UserMapper;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;
    
    @RequestMapping("/alluser")
    @ResponseBody
    public List<User> findAllUsers(){
        return userDao.findAllUser();
    }
    
    @RequestMapping(path="/find/{id}",method=RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable("id") int id){
        User user = userMapper.findById(id);
        return user;
    }
}
