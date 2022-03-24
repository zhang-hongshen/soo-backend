package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {
    String orderId;
    String productId;
    String productName;
    String departure;
    Float price;
    Integer num;
    Float amount;
}
