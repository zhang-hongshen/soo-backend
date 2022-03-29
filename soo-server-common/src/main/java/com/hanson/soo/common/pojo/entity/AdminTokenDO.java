package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("soo_admin_token")
public class AdminTokenDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("admin_id")
    String adminId;
    @TableField("token")
    String token;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
