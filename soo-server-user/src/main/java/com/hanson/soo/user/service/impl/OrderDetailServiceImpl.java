package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.soo.user.dao.OrderDetailDao;
import com.hanson.soo.user.service.OrderDetailService;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailDO>
        implements OrderDetailService {
}
