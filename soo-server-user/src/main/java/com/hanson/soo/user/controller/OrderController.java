package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.pojo.vo.OrderVO;
import com.hanson.soo.user.service.CartService;
import com.hanson.soo.user.service.OrderService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/query")
    public List<OrderVO> query(@RequestAttribute("userId")String userId, @RequestParam("state")Integer state){
        return orderService.listOrdersByUserIdAndState(userId, state).stream()
                .map(ConverterUtils::orderDTO2VO)
                .collect(Collectors.toList());
    }

    @PutMapping("/add")
    public String add(@RequestAttribute("userId")String userId,
                       @RequestBody List<OrderDetailDTO> orderDetailDTOs) {
        //删除购物车，如果直接购买则会省略这一步
        cartService.deleteCartsByUserIdAndProductId(
                userId,
                orderDetailDTOs.stream()
                        .map(OrderDetailDTO::getProductId)
                        .collect(Collectors.toList()));
        return orderService.insertOrder(userId, orderDetailDTOs);
    }

    @DeleteMapping("/delete/{orderId}")
    public boolean delete(@PathVariable("orderId")String orderId) {
        return orderService.delete(orderId);
    }

    @PostMapping("/pay/{orderId}")
    public boolean pay(@PathVariable("orderId")String orderId) {
        return orderService.pay(orderId);
    }

    @PostMapping("/refund/{orderId}")
    public boolean refund(@PathVariable("orderId")String orderId) {
        return orderService.refund(orderId);
    }
}
