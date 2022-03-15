package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.ProductInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.service.ProductInfoService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public PageListDTO<List<ProductInfoDTO>> listProductInfos(int current, int pageSize, ProductInfoDTO productInfoDTO) {
        LambdaQueryWrapper<ProductInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(productInfoDTO.getProductName()),ProductInfoDO::getProductName, productInfoDTO.getProductName());
        wrapper.eq(StringUtils.isNotBlank(productInfoDTO.getDeparture()), ProductInfoDO::getDeparture, productInfoDTO.getDeparture());
        wrapper.eq(StringUtils.isNotBlank(productInfoDTO.getDestination()),ProductInfoDO::getDestination, productInfoDTO.getDestination());
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), wrapper);
        List<ProductInfoDO> productInfoDOS = page.getRecords();
        List<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfoDO productInfoDO : productInfoDOS) {
            productInfoDTOS.add(ConverterUtils.productInfoDO2DTO(productInfoDO));
        }
        return new PageListDTO<>(productInfoDTOS, (int) page.getTotal());
    }

    @Override
    public int updateByProductId(ProductInfoDTO productInfoDTO) {
        ProductInfoDO productInfoDO = ConverterUtils.productInfoDTO2DO(productInfoDTO);
        LambdaUpdateWrapper<ProductInfoDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ProductInfoDO::getProductId, productInfoDO.getProductId());
        return productInfoDao.update(productInfoDO, wrapper);
    }

    @Override
    public int deleteByProductId(List<String> productIds) {
        LambdaQueryWrapper<ProductInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(ProductInfoDO::getProductId, productIds);
        return productInfoDao.delete(wrapper);
    }

    @Override
    public int insert(ProductInfoDTO productInfoDTO){
        ProductInfoDO productInfoDO = ConverterUtils.productInfoDTO2DO(productInfoDTO);
        String productId = String.valueOf(UUID.randomUUID());
        productInfoDO.setProductId(productId);
        return productInfoDao.insert(productInfoDO);
    }
}
