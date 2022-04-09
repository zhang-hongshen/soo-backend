package com.hanson.soo.user.controller;

import com.hanson.soo.user.pojo.dto.ChartDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.vo.ChartVO;
import com.hanson.soo.user.service.ChartService;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class ChartController {
    @Autowired
    private ChartService chartService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/query")
    public List<ChartVO> query(@RequestParam("userId")String userId){
        List<ChartDTO> chartDTOs = chartService.listCharts(userId);
        List<ChartVO> chartVOs = new ArrayList<>();
        chartDTOs.forEach(chartDTO -> {
            ChartVO chartVO = ConverterUtils.chartDTO2VO(chartDTO);
            ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(chartVO.getProductId());
            BeanUtils.copyProperties(productInfoDTO, chartVO);
            chartVOs.add(chartVO);
        });
        return chartVOs;
    }

    @PostMapping("/add")
    public boolean add(@RequestParam("userId") String userId,
                       @RequestBody ChartVO chartVO){
        ChartDTO chartDTO = ConverterUtils.chartVO2DTO(chartVO);
        chartDTO.setUserId(userId);
        return chartService.insertChart(chartDTO) > 0;
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam("userId") String userId,
                          @RequestBody  List<String> productIds){
        return chartService.deleteChartsByUserIdAndProductId(userId, productIds) == productIds.size();
    }

    @PutMapping("/update")
    public boolean update(@RequestParam("userId") String userId,
                          @RequestBody  List<ChartVO> chartVOs){
        List<ChartDTO> chartDTOs = new ArrayList<>();
        chartVOs.forEach(chartVO -> {
            ChartDTO chartDTO = ConverterUtils.chartVO2DTO(chartVO);
            chartDTO.setUserId(userId);
            chartDTOs.add(chartDTO);
        });
        chartService.updateChartByUserId(userId, chartDTOs);
        return true;
    }
}
