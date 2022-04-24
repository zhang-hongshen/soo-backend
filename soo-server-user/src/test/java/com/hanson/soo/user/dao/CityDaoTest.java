package com.hanson.soo.user.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class CityDaoTest {
    @Autowired
    private CityDao cityDao;

    @Test
    void listCityNames() {
        System.out.println(cityDao.listCityName());
        ReentrantLock lock = new ReentrantLock();
        lock.hasWaiters(lock.newCondition());
    }
}