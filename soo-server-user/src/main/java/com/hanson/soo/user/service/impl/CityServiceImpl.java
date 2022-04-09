package com.hanson.soo.user.service.impl;

import com.hanson.soo.common.dao.CityDao;
import com.hanson.soo.common.pojo.entity.CityDO;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final String REDIS_KEY= "soo:city";

    @Override
    public Set<String> listCities() {
        Set<String> cities = stringRedisTemplate.opsForSet().members(REDIS_KEY);
        if (cities == null || cities.isEmpty()) {
            List<CityDO> cityDOs = cityDao.selectList(null);
            cities = new HashSet<>();
            for (CityDO cityDO : cityDOs) {
                cities.add(cityDO.getName());
            }
        }
        return cities;
    }
}
