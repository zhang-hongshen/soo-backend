package com.hanson.soo.admin.service;

import com.hanson.soo.admin.pojo.dto.ProductDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.qo.ProductQO;
import com.hanson.soo.common.pojo.dto.PageDTO;

import java.util.List;

public interface ProductService {

    PageDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductQO productQO);

    boolean insert(ProductDTO productDTO);
    ProductDTO getProductByProductId(String productId);
    boolean updateProductByProductId(ProductDTO productDTO);
    boolean deleteByProductIds(List<String> productIds);
}
