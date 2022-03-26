package com.hanson.soo.user.service.impl;

import com.hanson.soo.user.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl<K, V> implements RedisService<K, V> {
    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public void expire(K key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void put(K key, V value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public V sPop(K key) {
        return redisTemplate.opsForSet().pop(key);
    }

    @Override
    public void sAdd(K key, V value) {
        redisTemplate.opsForSet().add(key,value);
    }

    @Override
    public Set<V> sMembers(K key) {
        return redisTemplate.opsForSet().members(key);
    }
}
