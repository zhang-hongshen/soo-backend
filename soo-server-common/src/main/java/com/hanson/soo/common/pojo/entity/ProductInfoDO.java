package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("soo_product_info")
public class ProductInfoDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("product_id")
    String productId;
    @TableField("product_name")
    String productName;
    @TableField("destination")
    String destination;
    @TableField("price")
    BigDecimal price;
    @TableField("status")
    Boolean status;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
