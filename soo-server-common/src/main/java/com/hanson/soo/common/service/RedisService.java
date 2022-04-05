package com.hanson.soo.common.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    void expire(String key, long time, TimeUnit timeUnit);
    Object get(String key);
    void set(String key, Object value);
    void set(String key, Object value, long time, TimeUnit timeUnit);
    Object sPop(String key);
    void sAdd(String key, Object value);
    Set<Object> sMembers(String key);

}
