package com.hanson.soo.user.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {
    String productId;
    String productName;
    String destination;
    String departure;
    String imageUrl;
    BigDecimal price;
}
