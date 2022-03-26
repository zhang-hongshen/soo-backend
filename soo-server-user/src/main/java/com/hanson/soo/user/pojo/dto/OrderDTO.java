package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    String orderId;
    List<OrderDetailDTO> orderDetails;
    Float totalAmount;
    Date createTime;
}
