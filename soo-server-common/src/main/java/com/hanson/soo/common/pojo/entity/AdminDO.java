package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

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
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
}
