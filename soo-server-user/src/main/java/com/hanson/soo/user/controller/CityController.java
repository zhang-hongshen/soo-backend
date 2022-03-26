package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.dto.CityDTO;
import com.hanson.soo.user.service.CityService;
import com.hanson.soo.user.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private RedisService<String,String> redisService;
    @Autowired
    private CityService cityService;

    private final String REDIS_KEY = "city:name";

    @GetMapping
    public List<String> query(){
        Set<String> res = redisService.sMembers(REDIS_KEY);
        List<String> cities = new ArrayList<>(res);
        if(cities.isEmpty()){
            for(CityDTO cityDTO : cityService.query()){
                String city = cityDTO.getName();
                cities.add(city);
                redisService.sAdd(REDIS_KEY, city);
            }
        }
        return cities;
    }
}
