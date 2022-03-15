package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.util.List;


@Data
public class ProductDetailDTO {
    String productId;
    List<String> imageUrls;
}
