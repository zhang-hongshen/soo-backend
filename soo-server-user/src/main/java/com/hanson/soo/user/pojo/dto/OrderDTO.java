package com.hanson.soo.user.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    String orderId;
    List<OrderDetailDTO> orderDetails;
    BigDecimal totalAmount;
    Integer status;
    Date paymentTime;
    Date createTime;
}
