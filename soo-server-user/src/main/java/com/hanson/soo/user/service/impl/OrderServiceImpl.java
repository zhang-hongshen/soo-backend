package com.hanson.soo.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import com.hanson.soo.common.utils.UUIDUtils;
import com.hanson.soo.user.pojo.OrderStatusEnum;
import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.service.OrderDetailService;
import com.hanson.soo.user.service.OrderInfoService;
import com.hanson.soo.user.service.OrderDetailService;
import com.hanson.soo.user.service.OrderService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    @Transactional
    public String insertOrder(String userId, List<OrderDetailDTO> orderDetailDTOs){
        String orderId = UUIDUtils.getId();
        BigDecimal totalAmount = new BigDecimal(0);
        List<OrderDetailDO> orderDetailDOs = new ArrayList<>(orderDetailDTOs.size());
        for(OrderDetailDTO orderDetailDTO : orderDetailDTOs){
            BigDecimal amount = orderDetailDTO.getPrice().multiply(new BigDecimal(orderDetailDTO.getNum()));
            totalAmount = totalAmount.add(amount);
            OrderDetailDO orderDetailDO = ConverterUtils.orderDetailDTO2DO(orderDetailDTO);
            orderDetailDO.setOrderId(orderId);
            orderDetailDO.setAmount(amount);
            orderDetailDOs.add(orderDetailDO);
        }
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoDO.setOrderId(orderId);
        orderInfoDO.setUserId(userId);
        orderInfoDO.setTotalAmount(totalAmount);
        orderInfoDO.setStatus(OrderStatusEnum.TO_BE_PAY.getStatus());
        return orderInfoService.save(orderInfoDO) &&
                orderDetailService.saveBatch(orderDetailDOs) ? orderId : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> listOrdersByUserIdAndStatus(String userId, Integer status) {
        List<OrderInfoDO> orderInfoDOs = orderInfoService.list(new LambdaQueryWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getUserId, userId)
                .eq( !status.equals(OrderStatusEnum.ALL.getStatus()), OrderInfoDO::getStatus, status)
                .orderByDesc(OrderInfoDO::getCreateTime));
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(OrderInfoDO orderInfoDO : orderInfoDOs){
            String orderId = orderInfoDO.getOrderId();
            List<OrderDetailDO> orderDetailDOs = orderDetailService.list(new LambdaQueryWrapper<OrderDetailDO>()
                    .eq(OrderDetailDO::getOrderId, orderId));
            List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
            orderDetailDOs.forEach(orderDetailDO -> orderDetailDTOs.add(ConverterUtils.orderDetailDO2DTO(orderDetailDO)));
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderInfoDO, orderDTO);
            orderDTO.setOrderDetails(orderDetailDTOs);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }


    @Override
    @Transactional
    public boolean pay(String orderId) {
        if (!getStatusByOrderId(orderId).equals(OrderStatusEnum.TO_BE_PAY.getStatus())) {
            return false;
        }
        System.out.println("请求支付服务！");
        System.out.println("订单支付成功！");
        return orderInfoService.update(null, new LambdaUpdateWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId)
                .set(OrderInfoDO::getStatus, OrderStatusEnum.PAID.getStatus())
                .set(OrderInfoDO::getPaymentTime, new Date())
        );
    }

    @Override
    @Transactional
    public boolean refund(String orderId) {
        if (!getStatusByOrderId(orderId).equals(OrderStatusEnum.PAID.getStatus())) {
            return false;
        }
        System.out.println("申请退款中！");
        return orderInfoService.update(null, new LambdaUpdateWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId)
                .set(OrderInfoDO::getStatus, OrderStatusEnum.REFUNDING.getStatus())
                .set(OrderInfoDO::getPaymentTime, new Date())
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getStatusByOrderId(String orderId) {
        OrderInfoDO orderInfoDO = orderInfoService.getOne(new LambdaQueryWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId));
        return orderInfoDO == null ? null : orderInfoDO.getStatus();
    }
}