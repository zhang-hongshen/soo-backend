package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.user.dao.ProductDepartureDao;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.user.service.ProductDepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDepartureServiceImpl implements ProductDepartureService {
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    public List<ProductDepartureDO> listProductDeparturesByProductId(String productId) {
        return productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .eq(ProductDepartureDO::getProductId, productId));
    }
}
