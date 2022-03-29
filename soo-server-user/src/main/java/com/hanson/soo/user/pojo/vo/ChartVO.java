package com.hanson.soo.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ChartVO {
    String productId;
    String productName;
    String imageUrl;
    String departure;
    Integer num;
    BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date;
}
