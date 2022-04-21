package com.hanson.soo.admin.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    String productId;
    String productName;
    String destination;
    BigDecimal price;
    Integer status;
    List<String> departures;
    List<String> imageUrls;
}
