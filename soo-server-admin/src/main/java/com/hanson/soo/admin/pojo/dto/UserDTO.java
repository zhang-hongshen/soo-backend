package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    public String userId;
    public String username;
    public String phone;
    public String password;
    public Timestamp createTime;
}
