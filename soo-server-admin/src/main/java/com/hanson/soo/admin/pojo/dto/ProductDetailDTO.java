package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class ProductDetailDTO {
    String productId;
    String productName;
    String destination;
    BigDecimal price;
    Boolean status;
    List<String> departures;
    List<String> imageUrls;
}
