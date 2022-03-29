package com.hanson.soo.user.controller;


import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY = "city:name";

    @GetMapping
    public List<Object> query(){
        Set<Object> res = redisService.sMembers(REDIS_KEY);
        List<Object> cities = new ArrayList<>(res);
        return cities;
    }
}
