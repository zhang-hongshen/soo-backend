package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDTO {
    String productId;
    String userId;
    String content;
    LocalDateTime createTime;
}
