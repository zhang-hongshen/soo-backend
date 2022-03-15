package com.hanson.soo.user.service;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;

import java.util.List;

public interface RecommendService {
    PageListDTO<List<ProductInfoDTO>> query(int current, int pageSize, String userId, ProductInfoDTO productInfoDTO);
}
