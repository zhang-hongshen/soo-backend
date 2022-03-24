package com.hanson.soo.user.service;

import com.hanson.soo.user.pojo.dto.ProductDetailDTO;

public interface ProductDetailService{
     ProductDetailDTO getProductDetailByProductId(String productId);
}
