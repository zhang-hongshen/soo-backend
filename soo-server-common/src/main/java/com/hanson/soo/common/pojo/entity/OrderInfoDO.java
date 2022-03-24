package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("soo_order_info")
public class OrderInfoDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("order_id")
    String orderId;
    @TableField("user_id")
    String userId;
    @TableField("total_amount")
    Float totalAmount;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
}
