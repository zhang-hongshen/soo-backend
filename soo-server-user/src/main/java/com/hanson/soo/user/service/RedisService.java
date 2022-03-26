package com.hanson.soo.user.service;

import java.util.Set;

public interface RedisService<K, V> {
    void expire(K key, long time);
    Object get(K key);
    void put(K key, V value);
    Object sPop(K key);
    void sAdd(K key, V value);
    Set<V> sMembers(K key);

}
