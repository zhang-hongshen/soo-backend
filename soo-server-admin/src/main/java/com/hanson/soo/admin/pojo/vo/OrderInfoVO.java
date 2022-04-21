package com.hanson.soo.admin.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfoVO {
    String orderId;
    String userId;
    BigDecimal totalAmount;
    String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date paymentTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;
}
