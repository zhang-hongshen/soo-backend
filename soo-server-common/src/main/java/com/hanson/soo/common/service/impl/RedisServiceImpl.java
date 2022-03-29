package com.hanson.soo.common.service.impl;

import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public  void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public  Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public  void put(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public  Object sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    public  void sAdd(String key, Object value) {
        redisTemplate.opsForSet().add(key,value);
    }

    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
