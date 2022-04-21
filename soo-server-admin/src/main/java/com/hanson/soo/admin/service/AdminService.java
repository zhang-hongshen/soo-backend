package com.hanson.soo.admin.service;


import com.hanson.soo.admin.pojo.dto.AdminDTO;
import com.hanson.soo.common.pojo.entity.AdminDO;

public interface AdminService {
    AdminDO getByPhone(String phone);
    int updateByAdminId(AdminDO adminDO);
    String getAdminIdByToken(String token);
    String refreshTokenByAdminId(String adminId);
    String getToken(AdminDTO adminDTO);
    AdminDTO getAdminInfoByToken(String token);
    boolean deleteToken(String token);
}
