package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetailDTO {
    String orderId;
    String productId;
    String productName;
    String departure;
    Date date;
    Float price;
    Integer num;
    Float amount;
}
