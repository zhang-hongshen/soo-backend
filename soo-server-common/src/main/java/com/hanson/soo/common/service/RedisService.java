package com.hanson.soo.common.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    void expire(String key, long time, TimeUnit timeUnit);
    String get(String key);
    void set(String key, String value);
    void set(String key, String value, long time, TimeUnit timeUnit);
    String sPop(String key);
    void sAdd(String key, String value);
    Set<String> sMembers(String key);

}
