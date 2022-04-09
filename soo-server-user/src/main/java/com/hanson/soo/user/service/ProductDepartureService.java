package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.entity.ProductDepartureDO;

import java.util.List;

public interface ProductDepartureService {
    List<ProductDepartureDO> listProductDeparturesByProductId (String productId);
}
