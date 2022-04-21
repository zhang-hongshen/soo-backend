package com.hanson.soo.admin.pojo.qo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductQO {
    String productId;
    String productName;
    String departure;
    String destination;
    BigDecimal price;
    String status;
}
