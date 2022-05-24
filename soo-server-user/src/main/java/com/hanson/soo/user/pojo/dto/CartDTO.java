package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CartDTO {
    String productId;
    String userId;
    String departure;
    Integer num;
    LocalDate travelDate;
}
