package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("soo_user_cart")
public class CartDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("user_id")
    String userId;
    @TableField("product_id")
    String productId;
    @TableField("departure")
    String departure;
    @TableField("num")
    Integer num;
    @TableField("travel_date")
    LocalDate travelDate;
    @TableField("create_time")
    LocalDateTime createTime;
    @TableField("update_time")
    LocalDateTime updateTime;
}
