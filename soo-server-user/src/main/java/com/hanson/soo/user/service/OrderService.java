package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderService {
    int insert(String userId, List<OrderDetailDTO> orderDetailDTOs);
    List<OrderDTO> queryByUserId(@RequestParam("userId")String userId);
}
