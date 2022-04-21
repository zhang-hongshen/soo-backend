package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.soo.user.dao.OrderInfoDao;
import com.hanson.soo.user.service.OrderInfoService;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoDO>
        implements OrderInfoService {
}
