package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("soo_city")
public class CityDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("name")
    String name;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
