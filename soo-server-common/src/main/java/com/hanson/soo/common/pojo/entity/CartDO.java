package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

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
    @TableField("date")
    Date date;
    @TableField("status")
    Boolean status;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
