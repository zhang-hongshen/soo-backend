package com.hanson.soo.admin.service;


import com.hanson.soo.admin.pojo.dto.AdminDTO;

import java.time.LocalDateTime;

public interface AdminService {
    String getAdminIdByToken(String token);
    String refreshTokenByAdminId(String adminId);
    String getToken(AdminDTO adminDTO);
    LocalDateTime getTokenUpdateTimeByAdminId(String adminId);
    AdminDTO getAdminInfoByAdminId(String token);
    boolean deleteToken(String token);
}
