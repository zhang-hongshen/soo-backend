package com.hanson.soo.user.pojo.vo;

import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderVO {
    String orderId;
    List<OrderDetailDTO> orderDetails;
    Float totalAmount;
    Date createTime;
}
