package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("soo_user_chart")
public class ChartDO {
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
