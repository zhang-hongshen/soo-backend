package com.hanson.soo.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.common.pojo.OrderEvent;
import com.hanson.soo.common.utils.UUIDUtils;
import com.hanson.soo.common.pojo.OrderState;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.service.OrderDetailService;
import com.hanson.soo.user.service.OrderInfoService;
import com.hanson.soo.user.service.OrderService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderDetailService orderDetailService;

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

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
        orderInfoDO.setState(OrderState.SUBMITTED.getState());
        return orderInfoService.save(orderInfoDO) &&
                orderDetailService.saveBatch(orderDetailDOs) ? orderId : null;
    }

    @Override
    public List<OrderDTO> listOrdersByUserIdAndState(String userId, Integer state) {
        List<OrderInfoDO> orderInfoDOs = orderInfoService.list(new LambdaQueryWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getUserId, userId)
                .eq( !state.equals(OrderState.ALL.getState()), OrderInfoDO::getState, state)
                .orderByDesc(OrderInfoDO::getCreateTime));
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(OrderInfoDO orderInfoDO : orderInfoDOs){
            String orderId = orderInfoDO.getOrderId();
            List<OrderDetailDO> orderDetailDOs = orderDetailService.list(new LambdaQueryWrapper<OrderDetailDO>()
                    .eq(OrderDetailDO::getOrderId, orderId));
            List<OrderDetailDTO> orderDetailDTOs = orderDetailDOs.stream()
                    .map(ConverterUtils::orderDetailDO2DTO)
                    .collect(Collectors.toList());
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderInfoDO, orderDTO);
            orderDTO.setOrderDetails(orderDetailDTOs);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    @Transactional
    public boolean delete(String orderId) {
        return orderInfoService.remove(new LambdaUpdateWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId)) &&
                orderDetailService.remove(new LambdaUpdateWrapper<OrderDetailDO>()
                        .eq(OrderDetailDO::getOrderId, orderId));
    }


    @Override
    @Transactional
    public boolean pay(String orderId) {
        if (!Objects.equals(getStateByOrderId(orderId), OrderState.SUBMITTED.getState())) {
            return false;
        }
        logger.info("请求支付服务！");
        logger.info("订单支付成功！");
        return orderInfoService.update(null, new LambdaUpdateWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId)
                .set(OrderInfoDO::getState, OrderState.PAID.getState())
                .set(OrderInfoDO::getPaymentTime, new Date())
        );
    }

    @Override
    public boolean refund(String orderId) {
        if (!Objects.equals(getStateByOrderId(orderId), OrderState.PAID.getState())) {
            return false;
        }
        logger.info("申请退款中！");
        return orderInfoService.update(null, new LambdaUpdateWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId)
                .set(OrderInfoDO::getState, OrderState.REFUNDING.getState())
                .set(OrderInfoDO::getPaymentTime, new Date())
        );
    }

    @Override
    public Integer getStateByOrderId(String orderId) {
        OrderInfoDO orderInfoDO = orderInfoService.getOne(new LambdaQueryWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getOrderId, orderId));
        return orderInfoDO == null ? null : orderInfoDO.getState();
    }
}