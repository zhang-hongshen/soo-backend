package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDTO {
    String userId;
    String username;
    String phone;
    String password;
    Date createTime;
}
