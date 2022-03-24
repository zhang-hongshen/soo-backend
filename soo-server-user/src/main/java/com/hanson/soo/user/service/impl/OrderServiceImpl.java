package com.hanson.soo.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.common.dao.OrderDetailDao;
import com.hanson.soo.common.dao.OrderInfoDao;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import com.hanson.soo.user.pojo.dto.OrderDTO;
import com.hanson.soo.user.pojo.dto.OrderDetailDTO;
import com.hanson.soo.user.service.OrderService;
import com.hanson.soo.user.utils.ConverterUtils;
import com.hanson.soo.user.utils.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    public int insert(String userId, List<OrderDetailDTO> orderDetailDTOs){
        String orderId = UUIDUtils.getId();
        float totalAmount = 0.0f;
        for(OrderDetailDTO orderDetailDTO : orderDetailDTOs){
            float amount = orderDetailDTO.getPrice() * orderDetailDTO.getNum();
            totalAmount += amount;
            OrderDetailDO orderDetailDO = ConverterUtils.orderDetailDTO2DO(orderDetailDTO);
            orderDetailDO.setAmount(amount);
            orderDetailDO.setOrderId(orderId);
            orderDetailDao.insert(orderDetailDO);
        }
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoDO.setOrderId(orderId);
        orderInfoDO.setUserId(userId);
        orderInfoDO.setTotalAmount(totalAmount);
        return orderInfoDao.insert(orderInfoDO);
    }

    @Override
    public List<OrderDTO> queryByUserId(String userId) {
        List<OrderInfoDO> orderInfoDOs = orderInfoDao.selectList(new LambdaQueryWrapper<OrderInfoDO>()
                .eq(OrderInfoDO::getUserId, userId));
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(OrderInfoDO orderInfoDO : orderInfoDOs){
            String orderId = orderInfoDO.getOrderId();
            List<OrderDetailDO> orderDetailDOs = orderDetailDao.selectList(new LambdaQueryWrapper<OrderDetailDO>()
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

}
