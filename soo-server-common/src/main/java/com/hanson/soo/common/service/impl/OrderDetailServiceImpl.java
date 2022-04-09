package com.hanson.soo.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.soo.common.dao.OrderDetailDao;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import com.hanson.soo.common.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailDO>
        implements OrderDetailService{
}
