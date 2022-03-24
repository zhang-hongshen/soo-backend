package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.util.List;


@Data
public class ProductDetailDTO {
    List<String> imageUrls;
    List<String> departures;
}
