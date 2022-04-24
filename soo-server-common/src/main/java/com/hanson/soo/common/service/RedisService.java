package com.hanson.soo.common.service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    void expire(String key, long time, TimeUnit timeUnit);
    String get(String key);
    void set(String key, String value);
    void set(String key, String value, long time, TimeUnit timeUnit);
    String sPop(String key);
    void sAdd(String key, String... values);
    Set<String> sMembers(String key);

    void delete(Collection<String> keys);

    boolean exists(String key);
    void delete(String key);
}
