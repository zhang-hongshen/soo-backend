package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.entity.ProductImageDO;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDO> listProductImagesByProductId(String productId);
    String getProductImageUrlByProductId(String productId);
}
