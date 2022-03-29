package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfoDTO {
    String orderId;
    String userId;
    BigDecimal totalAmount;
    Date createTime;
}
