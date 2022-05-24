package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.admin.dao.OrderDetailDao;
import com.hanson.soo.admin.dao.OrderInfoDao;
import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.admin.pojo.qo.OrderQO;
import com.hanson.soo.admin.service.OrderService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.OrderState;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public PageDTO<List<OrderInfoDTO>> listOrderInfos(int current, int pageSize, OrderQO query) {
        IPage<OrderInfoDO> page = orderInfoDao.selectPage(new Page<>(current, pageSize),
                new LambdaQueryWrapper<OrderInfoDO>()
                        .like(StringUtils.isNotBlank(query.getOrderId()), OrderInfoDO::getOrderId, query.getOrderId())
                        .like(StringUtils.isNotBlank(query.getUserId()), OrderInfoDO::getUserId, query.getUserId())
                        .eq(!query.getState().equals(OrderState.ALL.getValue()), OrderInfoDO::getState, OrderState.getStateByValue(query.getState())));
        List<OrderInfoDTO> orderInfoDTOs = page.getRecords().stream()
                .map(ConverterUtils::orderInfoDO2DTO)
                .collect(Collectors.toList());
        return new PageDTO<>(orderInfoDTOs, (int) page.getTotal());
    }


    @Override
    @Transactional
    public boolean deleteByOrderIds(List<String> orderIds) {
        orderDetailDao.delete(new LambdaUpdateWrapper<OrderDetailDO>()
                .in(OrderDetailDO::getOrderId, orderIds));
        return orderInfoDao.delete(new LambdaUpdateWrapper<OrderInfoDO>()
                .in(OrderInfoDO::getOrderId, orderIds)) == orderIds.size();
    }
}
