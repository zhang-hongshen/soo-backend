package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    LocalDateTime date;
    @TableField("price")
    BigDecimal price;
    @TableField("num")
    Integer num;
    @TableField("amount")
    BigDecimal amount;
    @TableField("create_time")
    LocalDateTime createTime;
    @TableField("update_time")
    LocalDateTime updateTime;
}
