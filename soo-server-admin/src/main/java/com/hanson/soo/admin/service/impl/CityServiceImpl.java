package com.hanson.soo.admin.service.impl;

import com.hanson.soo.admin.dao.CityDao;
import com.hanson.soo.admin.service.CityService;
import com.hanson.soo.admin.service.CityService;
import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private RedisService redisService;

    final String REDIS_KEY= "soo:city";

    @Override
    public List<String> listCityNames() {
        List<String> cities;
        if (!redisService.exists(REDIS_KEY) ||
                (cities = new ArrayList<>(redisService.sMembers(REDIS_KEY))).isEmpty()) {
            cities = cityDao.listCityNames();
            redisService.sAdd(REDIS_KEY, cities.toArray(new String[0]));
            redisService.expire(REDIS_KEY, 24, TimeUnit.HOURS);
        }
        return cities;
    }
}
