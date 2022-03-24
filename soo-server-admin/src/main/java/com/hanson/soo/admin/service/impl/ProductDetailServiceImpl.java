package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hanson.soo.common.dao.ProductImageDao;
import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.service.ProductDetailService;
import com.hanson.soo.admin.service.RedisService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private RedisService redisService;

    @Override
    public int insert(ProductDetailDTO productDetailDTO) {
        String productId = productDetailDTO.getProductId();
        for(String url : productDetailDTO.getImageUrls()){
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(url);
            productImageDO.setStatus(Boolean.FALSE);
            productImageDao.insert(productImageDO);
            redisService.sAdd(productId, productImageDO.getUrl());
        }
        return 0;
    }

    public int updateByProductId(ProductDetailDTO productDetailDTO){
        List<ProductImageDO> newProductImageDOs = ConverterUtils.productDetailDTO2imageDOList(productDetailDTO);
        LambdaQueryWrapper<ProductImageDO> queryWrapper = new LambdaQueryWrapper<>();
        LambdaUpdateWrapper<ProductImageDO> updateWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(ProductImageDO::getProductId, productDetailDTO.getProductId());
        queryWrapper.eq(ProductImageDO::getStatus, Boolean.FALSE);
        updateWrapper.eq(ProductImageDO::getProductId, productDetailDTO.getProductId());
        List<ProductImageDO> oldProductImageDOs = productImageDao.selectList(queryWrapper);
        //取出旧照片
        //判断新传过来的照片是否在照片已经存在
        //如果存在，则在新照片中移除该照片
        //如果不存在，则设置未历史图片，true
        //
        /**
         * 旧图片设置逻辑删除
         */
        for(ProductImageDO productImageDO : oldProductImageDOs){
            if(newProductImageDOs.contains(productImageDO)){
                newProductImageDOs.remove(productImageDO);
            }else{
                productImageDO.setStatus(Boolean.TRUE);//设置为历史图片
                productImageDao.update(productImageDO, updateWrapper);
            }
        }
        /**
         * 插入新图片
         */
        for (ProductImageDO productImageDO : newProductImageDOs) {
            productImageDO.setStatus(Boolean.FALSE);
            productImageDao.insert(productImageDO);
        }
        return 0;
    }


    public ProductDetailDTO getProductDetailByProductId(String productId){
        LambdaQueryWrapper<ProductImageDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductImageDO::getProductId, productId);
        queryWrapper.eq(ProductImageDO::getStatus, Boolean.FALSE);
        List<ProductImageDO> productImageDOs = productImageDao.selectList(queryWrapper);
        List<String> imageUrls = new ArrayList<>();
        productImageDOs.forEach((productImageDO) -> imageUrls.add(productImageDO.getUrl()));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setImageUrls(imageUrls);
        productDetailDTO.setProductId(productId);
        return productDetailDTO;
    }

    public int deleteByProductId(List<String> productIds){
        LambdaQueryWrapper<ProductImageDO> wrapper = new LambdaQueryWrapper<>();
        productIds.forEach((productId)->{
            wrapper.eq(ProductImageDO::getProductId, productId);
            productImageDao.delete(wrapper);
        });
        return 0;
    }
}
