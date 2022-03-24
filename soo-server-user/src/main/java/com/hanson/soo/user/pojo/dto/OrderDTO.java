package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDTO {
    String orderId;
    List<OrderDetailDTO> orderDetails;
    Float totalAmount;
    Timestamp createTime;
    Timestamp updateTime;
}
