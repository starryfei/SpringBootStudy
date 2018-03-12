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
    @Cacheable("stringKey")
    @RequestMapping(value = "/redis/string", method = RequestMethod.GET)
    public String insertString(@RequestParam(value = "name") String name) {
        stringRedisTemplate.opsForValue().set("stringKey", name);
        System.out.print(stringRedisTemplate.opsForValue().get("stringKey"));
        return  name;
    }
}
