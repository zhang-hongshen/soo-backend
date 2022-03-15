package com.hanson.soo.admin.service;


import com.hanson.soo.common.pojo.entity.AdminDO;

public interface AdminService {
    AdminDO getByPhone(String phone);
    int updateByAdminId(AdminDO adminDO);
}
