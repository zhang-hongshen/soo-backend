package com.hanson.soo.user.service;

import com.hanson.soo.user.pojo.dto.UserInfoDTO;

import java.time.LocalDateTime;

public interface UserService {
    String insertUser(UserInfoDTO userInfoDTO);
    boolean validatePhone(String phone);
    String refreshTokenByUserId(String userId);
    String getToken(UserInfoDTO userInfoDTO);
    String getUserIdByToken(String token);
    UserInfoDTO getUserInfoByUserId(String userId);

    LocalDateTime getTokenUpdateTimeByUserId(String userId);

    String getPasswordByUserId(String userId);

    String getUsernameByUserId(String userId);

    boolean updateBasicInfoByUserId(String userId, UserInfoDTO userInfoDTO);
    boolean updatePasswordByUserId(String userId, String password);
    boolean deleteUserToken(String token);
}
