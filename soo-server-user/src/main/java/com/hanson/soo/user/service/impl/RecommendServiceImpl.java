package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.ProductInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.service.RecommendService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageListDTO<List<ProductInfoDTO>> query(int current, int pageSize, String userId, ProductInfoDTO productInfoDTO) {
        LambdaQueryWrapper<ProductInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(productInfoDTO.getDeparture()),
                ProductInfoDO::getDeparture, productInfoDTO.getDeparture());
        wrapper.like(StringUtils.isNotBlank(productInfoDTO.getDestination()),
                ProductInfoDO::getDestination, productInfoDTO.getDestination());
        wrapper.eq(ProductInfoDO::getStatus, Boolean.TRUE);
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), wrapper);
        List<ProductInfoDO> productInfoDOS = page.getRecords();
        List<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfoDO productInfoDO : productInfoDOS) {
            productInfoDTOS.add(ConverterUtils.productInfoDO2DTO(productInfoDO));
        }
        return new PageListDTO<>(productInfoDTOS, (int) page.getTotal());
    }
}
