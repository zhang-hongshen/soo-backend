package com.hanson.soo.common.service;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;


@Data
class User implements Serializable {
    @JsonProperty("name")
    String name;
    @JsonProperty("age")
    Integer age;
}

@SpringBootTest
class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    void set() {
        User user = new User();
        user.setAge(11);
        user.setName("张鸿燊");
        redisService.set("111", JSON.toJSONString(user));
    }

    @Test
    void get() {
        String jsonStr = (String) redisService.get("111");
        User user = JSON.parseObject( jsonStr, User.class);
        System.out.println(user);
    }

    @Test
    void sMembers() {
        final String REDIS_KEY = "city:name";
        System.out.println(redisService.sMembers(REDIS_KEY));
    }
}