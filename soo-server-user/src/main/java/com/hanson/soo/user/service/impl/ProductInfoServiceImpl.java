package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.user.dao.ProductDepartureDao;
import com.hanson.soo.user.dao.ProductImageDao;
import com.hanson.soo.user.dao.ProductInfoDao;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.user.pojo.ProductStatusEnum;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    @Transactional(readOnly = true)
    public PageDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductQO query) {
        List<ProductInfoDO> productInfoDOs = productInfoDao.selectList(new LambdaQueryWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getStatus, ProductStatusEnum.ON_SALE.getStatus())
                .like(StringUtils.isNotBlank(query.getDestination()), ProductInfoDO::getDestination, query.getDestination())
                .like(StringUtils.isNotBlank(query.getProductName()), ProductInfoDO::getProductName, query.getProductName()));
        Set<String> productIds = productInfoDOs.stream()
                .map(ProductInfoDO::getProductId)
                .collect(Collectors.toSet());
        List<ProductDepartureDO> productDepartureDOs = productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .like(StringUtils.isNotBlank(query.getDeparture()), ProductDepartureDO::getDeparture, query.getDeparture()));
        Set<String> productIdsByDeparture = productDepartureDOs.stream()
                .map(ProductDepartureDO::getProductId)
                .collect(Collectors.toSet());
        //取交集
        productIds.retainAll(productIdsByDeparture);
        if(productIds.isEmpty()){
            return new PageDTO<>(new ArrayList<>(), 0);
        }
        //分页查询
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds)
                .eq(ProductInfoDO::getStatus, Boolean.TRUE));
        List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();
        for (ProductInfoDO productInfoDO : page.getRecords()) {
            ProductInfoDTO productInfoDTO = ConverterUtils.productInfoDO2DTO(productInfoDO);
            //获取产品封面照片
//            String imageUrl = productImageDao.selectOne(new LambdaQueryWrapper<ProductImageDO>()
//                    .eq(ProductImageDO::getProductId, productInfoDTO.getProductId())
//                    .last("limit 1")).getUrl();
//            productInfoDTO.setImageUrl(imageUrl);
            productInfoDTOs.add(productInfoDTO);
        }
        return new PageDTO<>(productInfoDTOs, (int) page.getTotal());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInfoDTO> listProductInfoByProductId(List<String> productIds) {
        List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();
        for(String productId : productIds){
            ProductInfoDO productInfoDO = productInfoDao.selectOne(new LambdaQueryWrapper<ProductInfoDO>()
                    .eq(ProductInfoDO::getProductId, productId));
            ProductInfoDTO productInfoDTO = ConverterUtils.productInfoDO2DTO(productInfoDO);
            //获取产品封面照片
            String imageUrl = productImageDao.selectOne(new LambdaQueryWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productInfoDTO.getProductId())
                    .last("limit 1")).getUrl();
            productInfoDTO.setImageUrl(imageUrl);
            productInfoDTOs.add(productInfoDTO);
        }
        return productInfoDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInfoDTO getProductInfoByProductId(String productId) {
        ProductInfoDO productInfoDO = productInfoDao.selectOne(new LambdaQueryWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getProductId, productId));
        ProductInfoDTO productInfoDTO = ConverterUtils.productInfoDO2DTO(productInfoDO);
        String imageUrl = productImageDao.selectOne(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productInfoDTO.getProductId())
                .last("limit 1")).getUrl();
        productInfoDTO.setImageUrl(imageUrl);
        return productInfoDTO;
    }
}
