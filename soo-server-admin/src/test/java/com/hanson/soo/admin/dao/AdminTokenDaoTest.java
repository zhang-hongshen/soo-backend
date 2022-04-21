package com.hanson.soo.admin.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminTokenDaoTest {
    @Autowired
    private AdminTokenDao adminTokenDao;

    @Test
    void insertOrUpdateTokenByAdminId() {
        String adminId = "047e5a6634574806855c664716702823";
        String token = "F8XTzP7c9VFRdvOptR2ZvQ==";
        adminTokenDao.insertOrUpdateTokenByAdminId(adminId, token);
    }
}