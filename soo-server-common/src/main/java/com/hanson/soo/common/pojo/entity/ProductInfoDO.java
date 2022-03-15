package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("soo_product")
public class ProductInfoDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("product_id")
    String productId;
    @TableField("product_name")
    String productName;
    @TableField("departure")
    String departure;
    @TableField("destination")
    String destination;
    @TableField("price")
    Float price;
    @TableField("status")
    Boolean status;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
}
