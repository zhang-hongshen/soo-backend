package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;

import java.util.List;

public interface ProductService {
    PageListDTO<List<ProductInfoDTO>> listInfo(int current, int pageSize, ProductQO query);
    List<ProductInfoDTO> predict(String userId);
    ProductDTO getByProductId(String productId);
}
