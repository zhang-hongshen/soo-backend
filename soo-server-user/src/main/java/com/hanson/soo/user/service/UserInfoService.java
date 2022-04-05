package com.hanson.soo.user.service;

import com.hanson.soo.user.pojo.dto.UserInfoDTO;

public interface UserInfoService {
    String insertUser(UserInfoDTO userInfoDTO);
    boolean checkPhone(String phone);
    String getToken(UserInfoDTO userInfoDTO);
    UserInfoDTO getUserInfoByToken(String token);
    UserInfoDTO getUserInfoByUserId(String userId);
    String getPasswordByUserId(String userId);
    int updateBasicInfoByUserId(String userId, UserInfoDTO userInfoDTO);
    int updatePasswordByUserId(String userId, String password);
}
