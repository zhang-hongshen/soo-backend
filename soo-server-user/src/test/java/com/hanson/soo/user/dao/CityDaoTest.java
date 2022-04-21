package com.hanson.soo.user.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityDaoTest {
    @Autowired
    private CityDao cityDao;

    @Test
    void listCityNames() {
        System.out.println(cityDao.listCityNames());
    }
}