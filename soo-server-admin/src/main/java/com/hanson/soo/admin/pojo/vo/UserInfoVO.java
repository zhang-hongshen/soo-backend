package com.hanson.soo.admin.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {
     String userId;
     String username;
     String phone;
     String password;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     Date createTime;
}
