package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;

import java.util.List;

public interface ProductInfoService {
    PageDTO<List<ProductInfoDTO>> listProductInfos(int current, int pageSize, ProductQO query);
    List<ProductInfoDTO> listProductInfosByProductId(List<String> productIds);
    ProductInfoDTO getProductInfoByProductId(String productId);
}
