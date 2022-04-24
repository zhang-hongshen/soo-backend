package com.hanson.soo.user.service.impl;

import com.hanson.soo.user.dao.ProductDepartureDao;
import com.hanson.soo.user.service.ProductDepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDepartureServiceImpl implements ProductDepartureService {
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    public List<String> listProductDepartureByProductId(String productId) {
        return productDepartureDao.listProductDepartureByProductId(productId);
    }
}
