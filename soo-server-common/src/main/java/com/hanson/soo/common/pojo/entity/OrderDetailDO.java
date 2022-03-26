package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

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
    @TableField("date")
    Date date;
    @TableField("price")
    Float price;
    @TableField("num")
    Integer num;
    @TableField("amount")
    Float amount;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
