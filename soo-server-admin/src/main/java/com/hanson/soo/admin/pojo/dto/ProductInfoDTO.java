package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String departure;
    String destination;
    BigDecimal price;
    Boolean status;
}
