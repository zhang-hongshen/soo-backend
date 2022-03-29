package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailDTO {
    String orderId;
    String productId;
    String productName;
    String departure;
    Date date;
    BigDecimal price;
    Integer num;
    BigDecimal amount;
}
