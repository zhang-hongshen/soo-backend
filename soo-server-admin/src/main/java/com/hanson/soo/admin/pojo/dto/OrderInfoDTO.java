package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderInfoDTO {
    String orderId;
    String userId;
    Integer state;
    BigDecimal totalAmount;
    LocalDateTime paymentTime;
    LocalDateTime createTime;
}
