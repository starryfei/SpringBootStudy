package com.starryfei.elastic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.starryfei.elastic.bean.User;
/**
 * 
 * @ClassName  UserDao   
 * @Description 基于 注解 实现 sql   
 * @author yafei.qin 
 * @date 2018年10月18日 下午2:54:42   
 *
 */
@Mapper
public interface UserDao {
    @Results({
        @Result(property = "id", column = "user_id"), //2
        @Result(property = "name", column = "user_name"),
        @Result(property = "pwd", column = "user_pwd")
    })
    @Select("Select * from sys_user")
    List<User> findAllUser();
    
}
