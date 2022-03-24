package com.hanson.soo.user.pojo.dto;

import lombok.Data;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String destination;
    String departure;
    String imageUrl;
    Float price;
}
