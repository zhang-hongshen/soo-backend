package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

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
    LocalDateTime createTime;
    @TableField("update_time")
    LocalDateTime updateTime;
}
