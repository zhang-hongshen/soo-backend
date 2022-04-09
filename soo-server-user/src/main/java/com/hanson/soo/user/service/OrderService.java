package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    String insertOrder(String userId, List<OrderDetailDTO> orderDetailDTOs);
    List<OrderDTO> listOrdersByUserIdAndStatus(String userId, Integer status);
    boolean pay(String orderId);
    boolean refund(String orderId);
    Integer getStatusByOrderId(String orderId);
}
