package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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
    BigDecimal totalAmount;
    @TableField("status")
    Integer status;
    @TableField("payment_time")
    LocalDateTime paymentTime;
    @TableField("create_time")
    LocalDateTime createTime;
    @TableField("update_time")
    LocalDateTime updateTime;
}
