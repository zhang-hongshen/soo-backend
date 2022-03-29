package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    String productId;
    String userId;
    String content;
    Date createTime;
}
