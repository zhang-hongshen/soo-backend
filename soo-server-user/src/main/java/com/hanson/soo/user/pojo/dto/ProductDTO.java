package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    String productId;
    String productName;
    String destination;
    BigDecimal price;
    List<String> imageUrls;
    List<String> departures;
}
