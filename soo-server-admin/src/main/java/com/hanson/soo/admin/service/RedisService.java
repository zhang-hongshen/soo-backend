package com.hanson.soo.admin.service;

import java.util.Set;

public interface RedisService {
    void expire(String key, long time);
    Object get(String key);
    void put(String key, Object value);
    Object sPop(String key);
    void sAdd(String key, Object value);
    Set<Object> sMembers(String key);

}
