package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String imageUrl;
    String destination;
    BigDecimal price;
    Integer state;
}
