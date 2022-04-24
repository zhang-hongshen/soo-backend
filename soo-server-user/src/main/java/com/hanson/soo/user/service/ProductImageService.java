package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.entity.ProductImageDO;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDO> listProductImageByProductId(String productId);
    String getProductImageUrlByProductId(String productId);
    List<String> listProductImageUrlByProductId(String productId);
}
