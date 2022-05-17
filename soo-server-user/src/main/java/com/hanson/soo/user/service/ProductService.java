package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;

import java.util.List;

public interface ProductService {
    PageDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductQO query);
    List<ProductInfoDTO> predict(String userId);
    ProductDTO getProductByProductId(String productId);
}
