package com.hanson.soo.user.service;


import com.hanson.soo.user.pojo.dto.ChartDTO;

import java.util.List;

public interface ChartService {
    int insertChart(ChartDTO chartDTO);
    List<ChartDTO> listCharts(String userId);
    int deleteChartsByUserIdAndProductId(String userId, List<String> productIds);
    int updateChartByUserId(String userId, List<ChartDTO> chartDTOs);
}
