package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("soo_user")
public class UserDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("user_id")
    String userId;
    @TableField("username")
    String username;
    @TableField("pwd")
    String password;
    @TableField("phone")
    String phone;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
}
