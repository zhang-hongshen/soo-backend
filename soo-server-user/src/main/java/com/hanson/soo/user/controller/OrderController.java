package com.hanson.soo.user.controller;


import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.pojo.vo.OrderVO;
import com.hanson.soo.user.service.ChartService;
import com.hanson.soo.user.service.OrderService;
import com.hanson.soo.user.utils.ConverterUtils;
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
    public List<OrderVO> query(@RequestParam("userId")String userId){
        List<OrderDTO> orderDTOs =  orderService.listByUserId(userId);
        List<OrderVO> orderVOs = new ArrayList<>();
        for (OrderDTO orderDTO :  orderDTOs) {
            orderVOs.add(ConverterUtils.orderDTO2VO(orderDTO));
        }
        return orderVOs;
    }

    @PutMapping("/add")
    public boolean add(@RequestParam("userId")String userId,
                       @RequestBody List<OrderDetailDTO> orderDetailDTOs){
        List<String> productIds = new ArrayList<>();
        orderDetailDTOs.forEach(orderDetailDTO -> productIds.add(orderDetailDTO.getProductId()));
        //删除购物车，如果直接购买则会省略这一步
        chartService.deleteByUserIdAndProductId(userId, productIds);
        //删除购物车后添加订单
        return orderService.insert(userId, orderDetailDTOs)  > 0;
    }
}
