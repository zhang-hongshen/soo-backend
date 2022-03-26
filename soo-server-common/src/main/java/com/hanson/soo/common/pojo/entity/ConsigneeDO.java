package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("soo_user_consignee")
public class ConsigneeDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("user_id")
    String userId;
    @TableField("consignee_name")
    String consigneeName;
    @TableField("phone")
    String phone;
    @TableField("address")
    String address;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
