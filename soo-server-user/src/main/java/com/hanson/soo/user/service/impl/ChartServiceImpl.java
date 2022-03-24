package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.common.dao.ChartDao;
import com.hanson.soo.common.pojo.entity.ChartDO;
import com.hanson.soo.user.pojo.dto.ChartDTO;
import com.hanson.soo.user.service.ChartService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private ChartDao chartDao;

    @Override
    public int insert(ChartDTO chartDTO) {
        ChartDO chartDO = chartDao.selectOne(new LambdaQueryWrapper<ChartDO>()
                .eq(ChartDO::getProductId, chartDTO.getProductId())
                .eq(ChartDO::getDeparture, chartDTO.getDeparture())
                .eq(ChartDO::getUserId, chartDTO.getUserId()));
        if(chartDO != null){
            chartDO.setNum(chartDO.getNum() + chartDTO.getNum());
            chartDao.updateById(chartDO);
            return 1;
        }
        return chartDao.insert(ConverterUtils.chartDTO2DO(chartDTO));
    }

    public List<ChartDTO> listChart(String userId){
        List<ChartDO> chartDOs = chartDao.selectList(new LambdaQueryWrapper<ChartDO>()
                .eq(ChartDO::getUserId, userId)
                .orderByDesc(ChartDO::getCreateTime));
        List<ChartDTO> chartDTOs = new ArrayList<>();
        chartDOs.forEach(chartDO -> chartDTOs.add(ConverterUtils.chartDO2DTO(chartDO)));
        return chartDTOs;
    }

    public int deleteByUserIdAndProductId(String userId, List<String> productIds){
        int count = 0;
        for(String productId : productIds){
            count += chartDao.delete(new LambdaQueryWrapper<ChartDO>()
                    .eq(ChartDO::getUserId, userId)
                    .eq(ChartDO::getProductId, productId));
        }
        return count;
    }
}
