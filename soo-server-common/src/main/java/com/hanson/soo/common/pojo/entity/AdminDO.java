package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("soo_admin")
public class AdminDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("admin_id")
    String adminId;
    @TableField("name")
    String name;
    @TableField("pwd")
    String password;
    @TableField("phone")
    String phone;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
