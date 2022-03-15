package com.hanson.soo.admin.service.impl;

import com.hanson.soo.admin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    @Override
    public void sAdd(String key, Object value) {
        redisTemplate.opsForSet().add(key,value);
    }

    @Override
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

}
