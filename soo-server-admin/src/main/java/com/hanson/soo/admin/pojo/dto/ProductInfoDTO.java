package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

@Data
public class ProductInfoDTO {
    String productId;
    String productName;
    String departure;
    String destination;
    Float price;
    Boolean status;
}
