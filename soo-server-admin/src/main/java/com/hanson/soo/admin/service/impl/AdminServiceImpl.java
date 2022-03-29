package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.common.dao.AdminDao;
import com.hanson.soo.admin.service.AdminService;
import com.hanson.soo.common.pojo.entity.AdminDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(readOnly = true)
    public AdminDO getByPhone(String phone) {
        LambdaQueryWrapper<AdminDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminDO::getPhone,phone);
        return adminDao.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public int updateByAdminId(AdminDO adminDO) {
        LambdaUpdateWrapper<AdminDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AdminDO::getAdminId, adminDO.getAdminId());
        return adminDao.update(adminDO,updateWrapper);
    }
}
