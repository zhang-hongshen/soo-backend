package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class OrderDetailDTO {
    String orderId;
    String productId;
    String productName;
    String departure;
    LocalDate date;
    BigDecimal price;
    Integer num;
    BigDecimal amount;
}
