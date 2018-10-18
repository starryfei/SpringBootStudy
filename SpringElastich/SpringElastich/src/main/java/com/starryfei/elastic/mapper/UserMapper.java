package com.starryfei.elastic.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starryfei.elastic.bean.User;
import com.starryfei.elastic.dao.UserDao1;
/**
 * 
 * @ClassName  UserMapper   
 * @Description 使用xml来实现sql 
 * @author yafei.qin 
 * @date 2018年10月18日 下午2:53:41   
 *
 */
@Component
public class UserMapper implements UserDao1 {
    @Autowired
    private SqlSession sqlSession;
    
    public User findById(int id) {
        return sqlSession.selectOne("findById", id);
    }

}
