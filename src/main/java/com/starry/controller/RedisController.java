package com.starry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName RedisController
 * @Description TODO使用redis缓存
 * @author yafei.qin
 * @date 2018年3月9日 下午6:45:40
 * 
 * @Copyright: 2012-2018 www.hirain.com Inc. All rights reserved.
 */
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 所以要是操作字符串的话，用StringRedisTemplate就可以了。
    // 但要是想要存储一个对象（比如：User）,我们就需要使用RedisTemplate，并对key采用string序列化方式，对value采用json序列化方式，这时候就需要对redisTemplate自定义配置了：
    // 在缓存的过程中，使用redis-cli查看所有的key值时发现有一些数据是二进制的字符串，上网搜索发现，缓存默认的是使用RedisTemplate    
    @RequestMapping(value = "/redis/get", method = RequestMethod.GET)
    @Cacheable(value="info",key="#name")
    //将方法的返回值放入到缓存中，方法调用前优先在缓存中查找，有则直接返回，不会执行方法
    public String insert(@RequestParam(value = "name") String name) {
        stringRedisTemplate.opsForValue().set("stringKey", name);
        System.out.print(stringRedisTemplate.opsForValue().get("stringKey"));
        return  name;
    }
    
    @RequestMapping(value = "/redis/put", method = RequestMethod.GET)
    @CachePut(value="info",key="#name")
    //将方法的返回值写入到缓存中，方法调用前不检查缓存，始终被调用
    public String put(@RequestParam(value = "name") String name) {
        System.out.print("执行了。。。"));
        return  name;
    }
    
    @RequestMapping(value = "/redis/delete", method = RequestMethod.GET)
    @CacheEvict(value="info",key="#name")
    //在缓存中清除缓存的值     
    public String delete(@RequestParam(value = "name") String name) {
        return  name;
    }
}
