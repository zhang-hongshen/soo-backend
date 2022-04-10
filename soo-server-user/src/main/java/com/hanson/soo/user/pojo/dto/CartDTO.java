package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CartDTO {
    String productId;
    String userId;
    String departure;
    Integer num;
    Date date;
}
