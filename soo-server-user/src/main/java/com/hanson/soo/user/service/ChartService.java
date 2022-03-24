package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.ChartDTO;

import java.util.List;

public interface ChartService {
    int insert(ChartDTO chartDTO);
    List<ChartDTO> listChart(String userId);
    int deleteByUserIdAndProductId(String userId, List<String> productIds);
}
