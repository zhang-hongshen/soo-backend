package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("soo_order_detail")
public class OrderDetailDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("order_id")
    String orderId;
    @TableField("product_id")
    String productId;
    @TableField("product_name")
    String productName;
    @TableField("departure")
    String departure;
    @TableField("price")
    Float price;
    @TableField("num")
    Integer num;
    @TableField("amount")
    Float amount;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
}
