package com.hanson.soo.admin.service;

import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;

import java.util.List;

public interface ProductDetailService {
    int insert(ProductDetailDTO productDetailDTO);
    int updateByProductId(ProductDetailDTO productDetailDTO);
    ProductDetailDTO getProductDetailByProductId(String productId);
    int deleteByProductId(List<String> productIds);
}
