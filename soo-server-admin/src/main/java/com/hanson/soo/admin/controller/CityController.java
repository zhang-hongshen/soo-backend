package com.hanson.soo.admin.controller;


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

    @GetMapping
    public List<Object> query() {
        Set<Object> res = redisService.sMembers("city:name");
        return new ArrayList<>(res);
    }
}
