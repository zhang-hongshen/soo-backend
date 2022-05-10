package com.hanson.soo.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CartVO {
    String productId;
    String productName;
    String imageUrl;
    String departure;
    Integer num;
    BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
