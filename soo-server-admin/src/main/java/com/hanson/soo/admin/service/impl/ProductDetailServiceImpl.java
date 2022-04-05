package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.service.ProductDetailService;
import com.hanson.soo.admin.utils.AliyunOSSUtils;
import com.hanson.soo.common.dao.ProductDepartureDao;
import com.hanson.soo.common.dao.ProductImageDao;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;

    @Override
    @Transactional
    public int insert(ProductDetailDTO productDetailDTO) {
        String productId = productDetailDTO.getProductId();
        for (String url : productDetailDTO.getImageUrls()) {
            System.out.println(url);
            System.out.println(AliyunOSSUtils.objectExistByUrl(url));
            //文件已经上传至阿里云
            if (AliyunOSSUtils.objectExistByUrl(url)) {
                ProductImageDO productImageDO = new ProductImageDO();
                productImageDO.setProductId(productId);
                productImageDO.setUrl(url);
                productImageDO.setStatus(Boolean.TRUE);
                productImageDao.insert(productImageDO);
            }
        }
        for (String departure: productDetailDTO.getDepartures()) {
            ProductDepartureDO productDepartureDO = new ProductDepartureDO();
            productDepartureDO.setProductId(productId);
            productDepartureDO.setDeparture(departure);
            productDepartureDao.insert(productDepartureDO);
        }
        return 0;
    }

    @Override
    @Transactional
    public int updateByProductId(ProductDetailDTO productDetailDTO){
        Set<ProductImageDO> newProductImageDOs = new HashSet<>();
        String productId = productDetailDTO.getProductId();
        productDetailDTO.getImageUrls().forEach((url) -> {
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(url);
            productImageDO.setStatus(Boolean.TRUE);
            newProductImageDOs.add(productImageDO);
        });
        Set<ProductImageDO> oldProductImageDOs = new HashSet<>(productImageDao.selectList(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productDetailDTO.getProductId())
                .eq(ProductImageDO::getStatus, Boolean.TRUE)));
        //求交集
        Set<ProductImageDO> intersection = new HashSet<>(oldProductImageDOs);
        intersection.retainAll(newProductImageDOs);
        //求差集
        oldProductImageDOs.removeAll(intersection);
        newProductImageDOs.removeAll(intersection);
        /**
         * 插入新图片
         */
        for (ProductImageDO productImageDO : newProductImageDOs) {
            productImageDO.setStatus(Boolean.TRUE);
            productImageDao.insert(productImageDO);
        }
        /**
         * 旧图片设置为历史图片
         */
        for (ProductImageDO productImageDO : oldProductImageDOs) {
            productImageDO.setStatus(Boolean.FALSE);
            productImageDao.update(productImageDO, new LambdaUpdateWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productDetailDTO.getProductId())
                    .eq(ProductImageDO::getUrl, productImageDO.getUrl()));
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailDTO getProductDetailByProductId(String productId) {
        List<ProductImageDO> productImageDOs = productImageDao.selectList(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productId)
                .eq(ProductImageDO::getStatus, Boolean.TRUE));
        List<ProductDepartureDO> productDepartureDOs = productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .eq(ProductDepartureDO::getProductId, productId));
        List<String> imageUrls = new ArrayList<>();
        productImageDOs.forEach((productImageDO) -> imageUrls.add(productImageDO.getUrl()));
        List<String> departures = new ArrayList<>();
        productDepartureDOs.forEach(productDepartureDO -> departures.add(productDepartureDO.getDeparture()));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setImageUrls(imageUrls);
        productDetailDTO.setDepartures(departures);
        return productDetailDTO;
    }

    @Override
    @Transactional
    public int deleteByProductId(List<String> productIds){
        for (String productId : productIds) {
            List<ProductImageDO> productImageDOs = productImageDao.selectList(new LambdaQueryWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productId));
            productImageDao.delete(new LambdaQueryWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productId));
            //删除阿里云里的东西
            for (ProductImageDO productImageDO : productImageDOs) {
                AliyunOSSUtils.deleteObjectByUrl(productImageDO.getUrl());
            }
            productDepartureDao.delete(new LambdaQueryWrapper<ProductDepartureDO>()
                    .eq(ProductDepartureDO::getProductId, productId));
        }
        return 0;
    }
}
