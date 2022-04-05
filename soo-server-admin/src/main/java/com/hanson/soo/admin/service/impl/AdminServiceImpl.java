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
        return adminDao.selectOne(new LambdaQueryWrapper<AdminDO>()
                .eq(AdminDO::getPhone,phone));
    }

    @Override
    @Transactional
    public int updateByAdminId(AdminDO adminDO) {
        return adminDao.update(adminDO,new LambdaUpdateWrapper<AdminDO>()
                .eq(AdminDO::getAdminId, adminDO.getAdminId()));
    }
}
