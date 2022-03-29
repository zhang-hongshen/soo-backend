package com.hanson.soo.user.controller;


import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.pojo.vo.OrderVO;
import com.hanson.soo.user.service.ChartService;
import com.hanson.soo.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/api/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ChartService chartService;

    @GetMapping("/query")
    public List<OrderDTO> query(@RequestParam("userId")String userId){
        return orderService.listByUserId(userId);
    }

    @PutMapping("/add")
    public boolean add(@RequestParam("userId")String userId,
                       @RequestBody List<OrderDetailDTO> orderDetailDTOs){
        List<String> productIds = new ArrayList<>();
        orderDetailDTOs.forEach(orderDetailDTO -> productIds.add(orderDetailDTO.getProductId()));
        //添加订单然后删除购物车
        return (orderService.insert(userId, orderDetailDTOs)  > 0 &&
                chartService.deleteByUserIdAndProductId(userId, productIds) == orderDetailDTOs.size());
    }
}
