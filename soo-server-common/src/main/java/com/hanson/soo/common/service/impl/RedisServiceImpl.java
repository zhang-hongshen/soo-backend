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

    @Override
    public void expire(String key, long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, time, timeUnit);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void set(String key, String value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key,value, time, timeUnit);
    }

    @Override
    public String sPop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    @Override
    public void sAdd(String key, String... values) {
        stringRedisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
