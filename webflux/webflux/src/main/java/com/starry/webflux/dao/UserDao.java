package com.starry.webflux.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.starry.webflux.bean.User;
@Repository
public interface UserDao extends CrudRepository<User,Long>{
    Iterable<User> findAll();
    User findById(String id);
    

}
