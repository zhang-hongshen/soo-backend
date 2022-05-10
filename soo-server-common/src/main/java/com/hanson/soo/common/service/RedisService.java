package com.hanson.soo.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    void expire(String key, long time, TimeUnit timeUnit);
    boolean exists(String key);
    void delete(String key);
    void delete(Collection<String> keys);

    // region string
    String get(String key);
    void set(String key, String value);
    void set(String key, String value, long time, TimeUnit timeUnit);
    boolean setNX(String key, String value);
    boolean setNX(String key, String value, long time, TimeUnit timeUnit);
    // endregion

    // region hash
    Object hGet(String key, Object hashKey);
    List<Object> hMGet(String key, List<Object> hashKeys);
    // endregion

    // region set
    String sPop(String key);
    void sAdd(String key, String... values);
    Set<String> sMembers(String key);
    // endregion
}
