package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;

import java.util.List;

public interface ProductInfoService {
    PageListDTO<List<ProductInfoDTO>> listInfo(int current, int pageSize, ProductInfoDTO productInfoDTO);
    List<ProductInfoDTO> listInfoByProductId(List<String> productIds);
    ProductInfoDTO getByProductId(String productId);
}
