package com.hanson.soo.common.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("soo_user_token")
public class UserTokenDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("user_id")
    String userId;
    @TableField("token")
    String token;
    @TableField("create_time")
    LocalDateTime createTime;
    @TableField("update_time")
    LocalDateTime updateTime;
}
