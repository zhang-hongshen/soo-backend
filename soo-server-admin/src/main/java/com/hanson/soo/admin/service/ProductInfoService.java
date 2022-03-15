package com.hanson.soo.admin.service;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;

import java.util.List;


public interface ProductInfoService {
    PageListDTO<List<ProductInfoDTO>> listProductInfos(int current, int pageSize, ProductInfoDTO productInfoDTO);
    int updateByProductId(ProductInfoDTO productInfoDTO);
    int deleteByProductId(List<String> productIds);
    int insert(ProductInfoDTO productInfoDTO);
}
