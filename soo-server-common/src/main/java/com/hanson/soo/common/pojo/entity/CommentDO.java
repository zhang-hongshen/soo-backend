package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("soo_comment")
public class CommentDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("product_id")
    String productId;
    @TableField("user_id")
    String userId;
    @TableField("content")
    String content;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;
}
