package com.hanson.soo.user.service;

import java.util.List;

public interface ProductImageService {
    String getProductImageUrlByProductId(String productId);
    List<String> listProductImageUrlByProductId(String productId);
}
