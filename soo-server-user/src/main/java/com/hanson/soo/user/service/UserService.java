package com.hanson.soo.user.service;

import com.hanson.soo.user.pojo.dto.UserInfoDTO;

public interface UserService {
    String insertUser(UserInfoDTO userInfoDTO);
    boolean validatePhone(String phone);
    boolean validateToken(String token);

    String refreshTokenByUserId(String userId);

    String getToken(UserInfoDTO userInfoDTO);
    UserInfoDTO getUserInfoByToken(String token);
    UserInfoDTO getUserInfoByUserId(String userId);
    String getPasswordByUserId(String userId);
    boolean updateBasicInfoByUserId(String userId, UserInfoDTO userInfoDTO);
    boolean updatePasswordByUserId(String userId, String password);
    boolean deleteUserToken(String token);
}
