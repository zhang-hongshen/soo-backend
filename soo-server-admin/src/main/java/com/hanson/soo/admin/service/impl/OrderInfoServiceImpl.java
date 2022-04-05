package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.admin.service.OrderInfoService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.dao.OrderInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoDao orderInfoDao;

    public PageListDTO<List<OrderInfoDTO>> listInfo(int current, int pageSize, OrderInfoDTO query) {
        IPage<OrderInfoDO> page = orderInfoDao.selectPage(new Page<>(current, pageSize),
                new LambdaQueryWrapper<OrderInfoDO>()
                        .like(StringUtils.isNotBlank(query.getOrderId()), OrderInfoDO::getOrderId, query.getOrderId())
                        .like(StringUtils.isNotBlank(query.getUserId()), OrderInfoDO::getUserId, query.getUserId()));
        List<OrderInfoDTO> orderInfoDTOs = new ArrayList<>();
        for (OrderInfoDO orderInfoDO : page.getRecords()) {
            OrderInfoDTO orderInfoDTO = ConverterUtils.orderInfoDO2DTO(orderInfoDO);
            orderInfoDTOs.add(orderInfoDTO);
        }
        return new PageListDTO<>(orderInfoDTOs, (int) page.getTotal());
    }
}
