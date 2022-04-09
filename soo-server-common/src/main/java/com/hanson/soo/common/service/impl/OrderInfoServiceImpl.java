package com.hanson.soo.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.soo.common.dao.OrderInfoDao;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import com.hanson.soo.common.service.OrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoDO>
        implements OrderInfoService {
}
