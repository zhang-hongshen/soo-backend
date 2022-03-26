package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("soo_user")
public class UserInfoDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("user_id")
    String userId;
    @TableField("username")
    String username;
    @TableField("avatar")
    String avatar;
    @TableField("pwd")
    String password;
    @TableField("phone")
    String phone;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
