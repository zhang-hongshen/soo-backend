package com.hanson.soo.user.pojo.dto;

import lombok.Data;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String departure;
    String destination;
    Float price;
}
