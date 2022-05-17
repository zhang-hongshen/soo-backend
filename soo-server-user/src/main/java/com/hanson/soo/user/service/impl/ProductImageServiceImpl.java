package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.user.dao.ProductImageDao;
import com.hanson.soo.user.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public String getProductImageUrlByProductId(String productId) {
        return productImageDao.selectOne(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productId)
                .eq(ProductImageDO::getState, Boolean.TRUE)
                .last("limit 1")).getUrl();
    }

    @Override
    public List<String> listProductImageUrlByProductId(String productId) {
        return productImageDao.listProductImageUrlByProductId(productId);
    }
}
