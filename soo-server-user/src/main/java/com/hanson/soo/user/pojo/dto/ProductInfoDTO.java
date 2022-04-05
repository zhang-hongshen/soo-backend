package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String destination;
    String imageUrl;
    BigDecimal price;
}
