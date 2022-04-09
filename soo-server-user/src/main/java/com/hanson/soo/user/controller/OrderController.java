package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.OrderStatus;
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

@RequestMapping("/api/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ChartService chartService;

    @GetMapping("/query")
    public List<OrderVO> query(@RequestParam("userId")String userId, @RequestParam("status")String status){
        List<OrderDTO> orderDTOs =  orderService.listOrdersByUserIdAndStatus(userId, OrderStatus.getStatusByValue(status));
        List<OrderVO> orderVOs = new ArrayList<>();
        for (OrderDTO orderDTO :  orderDTOs) {
            OrderVO orderVO = ConverterUtils.orderDTO2VO(orderDTO);
            orderVO.setStatus(OrderStatus.getValueByStatus(orderDTO.getStatus()));
            orderVOs.add(orderVO);
        }
        return orderVOs;
    }

    @PutMapping("/add")
    public String add(@RequestParam("userId")String userId,
                       @RequestBody List<OrderDetailDTO> orderDetailDTOs) {
        List<String> productIds = new ArrayList<>();
        orderDetailDTOs.forEach(orderDetailDTO -> productIds.add(orderDetailDTO.getProductId()));
        //删除购物车，如果直接购买则会省略这一步
        chartService.deleteChartsByUserIdAndProductId(userId, productIds);
        return orderService.insertOrder(userId, orderDetailDTOs);
    }

    @PostMapping("/pay")
    public boolean pay(@RequestBody String orderId) {
        return orderService.pay(orderId);
    }

    @PostMapping("/refund")
    public boolean refund(@RequestBody String orderId) {
        return orderService.refund(orderId);
    }
}
