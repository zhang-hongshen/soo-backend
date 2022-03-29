package com.hanson.soo.admin.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;



@Data
public class ProductVO {
    String productId;
    String productName;
    String destination;
    BigDecimal price;
    String status;
    List<String> departures;
    List<String> imageUrls;
}
