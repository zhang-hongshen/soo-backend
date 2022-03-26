package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.ProductDepartureDao;
import com.hanson.soo.common.dao.ProductImageDao;
import com.hanson.soo.common.dao.ProductInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    public PageListDTO<List<ProductInfoDTO>> listInfo(int current, int pageSize, ProductInfoDTO query) {
        List<ProductInfoDO> productInfoDOs = productInfoDao.selectList(new LambdaQueryWrapper<ProductInfoDO>()
                .like(StringUtils.isNotBlank(query.getDestination()), ProductInfoDO::getDestination, query.getDestination())
                .eq(ProductInfoDO::getStatus, Boolean.TRUE));
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
        //分页查询
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds));
        productInfoDOs = page.getRecords();
        List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();
        for (ProductInfoDO productInfoDO : productInfoDOs) {
            ProductInfoDTO productInfoDTO = ConverterUtils.productInfoDO2DTO(productInfoDO);
            //获取产品封面照片
            String imageUrl = productImageDao.selectOne(new LambdaQueryWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productInfoDTO.getProductId())
                    .last("limit 1")).getUrl();
            productInfoDTO.setImageUrl(imageUrl);
            productInfoDTOs.add(productInfoDTO);
        }
        return new PageListDTO<>(productInfoDTOs, (int) page.getTotal());
    }

    @Override
    public List<ProductInfoDTO> listInfoByProductId(List<String> productIds) {
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
    public ProductInfoDTO getByProductId(String productId) {
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
