package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.ProductDepartureDao;
import com.hanson.soo.common.dao.ProductInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.service.ProductInfoService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    @Transactional(readOnly = true)
    public PageListDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductInfoDTO query) {
        List<ProductInfoDO> productInfoDOs = productInfoDao.selectList(new LambdaQueryWrapper<ProductInfoDO>()
                .like(StringUtils.isNotBlank(query.getProductName()), ProductInfoDO::getProductName, query.getProductName())
                .like(StringUtils.isNotBlank(query.getDestination()), ProductInfoDO::getDestination, query.getDestination())
                .eq(query.getStatus() != null, ProductInfoDO::getStatus, query.getStatus()));
        Set<String> productIds = new HashSet<>();
        productInfoDOs.forEach((productInfoDO) -> productIds.add(productInfoDO.getProductId()));
        List<ProductDepartureDO> productDepartureDOs = productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .like(StringUtils.isNotBlank(query.getDeparture()), ProductDepartureDO::getDeparture, query.getDeparture()));
        Set<String> productIdsByDeparture = new HashSet<>();
        productDepartureDOs.forEach((productDepartureDO) -> productIdsByDeparture.add(productDepartureDO.getProductId()));
        //取交集
        productIds.retainAll(productIdsByDeparture);
        if(productIds.isEmpty()){
            return new PageListDTO<>(new ArrayList<>(), 0);
        }
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds));
        List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();
        for (ProductInfoDO productInfoDO : page.getRecords()) {
            productInfoDTOs.add(ConverterUtils.productInfoDO2DTO(productInfoDO));
        }
        return new PageListDTO<>(productInfoDTOs, (int) page.getTotal());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInfoDTO getByProductId(String productId) {
        ProductInfoDO productInfoDO = productInfoDao.selectOne(new LambdaQueryWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getProductId, productId));
        return ConverterUtils.productInfoDO2DTO(productInfoDO);
    }

    @Override
    public int updateByProductId(ProductInfoDTO productInfoDTO) {
        ProductInfoDO productInfoDO = ConverterUtils.productInfoDTO2DO(productInfoDTO);
        return productInfoDao.update(productInfoDO, new LambdaUpdateWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getProductId, productInfoDO.getProductId()));
    }

    @Override
    public int deleteByProductId(List<String> productIds) {
        return productInfoDao.delete(new LambdaQueryWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds));
    }

    @Override
    @Transactional
    public int insert(ProductInfoDTO productInfoDTO){
        ProductInfoDO productInfoDO = ConverterUtils.productInfoDTO2DO(productInfoDTO);
        return productInfoDao.insert(productInfoDO);
    }
}
