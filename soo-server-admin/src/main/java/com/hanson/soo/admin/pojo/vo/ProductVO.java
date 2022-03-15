package com.hanson.soo.admin.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    String productId;
    String productName;
    String departure;
    String destination;
    Float price;
    List<String> imageUrl;
}
