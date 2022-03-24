package com.hanson.soo.user.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ChartVO {
    String productId;
    String productName;
    String imageUrl;
    String departure;
    Integer num;
    Float price;
    Date date;
}
