package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.admin.dao.AdminDao;
import com.hanson.soo.admin.dao.AdminTokenDao;
import com.hanson.soo.admin.pojo.dto.AdminDTO;
import com.hanson.soo.admin.service.AdminService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.admin.utils.TokenUtils;
import com.hanson.soo.common.pojo.entity.AdminDO;
import com.hanson.soo.common.pojo.entity.AdminTokenDO;
import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminTokenDao adminTokenDao;
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY_PREFIX = "soo:admin:token";

    @Override
    public AdminDO getByPhone(String phone) {
        return adminDao.selectOne(new LambdaQueryWrapper<AdminDO>()
                .eq(AdminDO::getPhone,phone));
    }

    @Override
    public int updateByAdminId(AdminDO adminDO) {
        return adminDao.update(adminDO,new LambdaUpdateWrapper<AdminDO>()
                .eq(AdminDO::getAdminId, adminDO.getAdminId()));
    }

    @Override
    public String getAdminIdByToken(String token) {
        return adminTokenDao.getAdminIdByToken(token);
    }

    @Override
    public String refreshTokenByAdminId(String adminId) {
        String token = TokenUtils.createToken();
        // 更新或插入token
        adminTokenDao.insertOrUpdateTokenByAdminId(adminId, token);
        //更新后的token存入redis，value为用户信息
        redisService.set(REDIS_KEY_PREFIX + ":" + token, "", 1, TimeUnit.HOURS);
        return token;
    }

    @Override
    public String getToken(AdminDTO adminDTO) {
        AdminDO adminDO = adminDao.selectOne(new LambdaQueryWrapper<AdminDO>()
                .eq(AdminDO::getName, adminDTO.getName())
                .eq(AdminDO::getPassword, adminDTO.getPassword()));
        if (adminDO == null) {
            // 用户不存在
            return "";
        }
        return refreshTokenByAdminId(adminDO.getAdminId());
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDTO getAdminInfoByToken(String token){
        AdminTokenDO adminTokenDO = adminTokenDao.selectOne(new LambdaUpdateWrapper<AdminTokenDO>()
                .eq(AdminTokenDO::getToken, token));
        AdminDO adminDO = adminDao.selectOne(new LambdaUpdateWrapper<AdminDO>()
                .eq(AdminDO::getAdminId, adminTokenDO.getAdminId()));
        return ConverterUtils.adminDO2DTO(adminDO);
    }

    @Override
    public boolean deleteToken(String token) {
        redisService.delete(REDIS_KEY_PREFIX+ ":" + token);
        return adminTokenDao.delete(new LambdaUpdateWrapper<AdminTokenDO>().eq(AdminTokenDO::getToken, token)) > 0;
    }
}
