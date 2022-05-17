package com.hanson.soo.admin.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
    String productId;
    String productName;
    String departure;
    String destination;
    BigDecimal price;
    String state;
}
