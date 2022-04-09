package com.hanson.soo.common.service.impl;

import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void expire(String key, long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, time, timeUnit);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public void set(String key, String value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key,value);
        expire(key, time, timeUnit);
    }

    public String sPop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    public void sAdd(String key, String value) {
        stringRedisTemplate.opsForSet().add(key,value);
    }

    public Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }
}
