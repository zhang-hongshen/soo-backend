package com.hanson.soo.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.pojo.RedisKeyPrefix;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;
import com.hanson.soo.user.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductDepartureService productDepartureService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RecommendService recommendService;


    @Override
    public PageDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductQO query) {
        return productInfoService.listProductInfo(current, pageSize, query);
    }

    @Override
    public List<ProductInfoDTO> predict(String userId) {
        List<String> productIds = recommendService.predict(userId);
        return productInfoService.listProductInfoByProductId(productIds);
    }

    @Override
    public ProductDTO getProductByProductId(String productId) {
        // 查询缓存
        final String redisKey = RedisKeyPrefix.PRODUCT_INFO.getPrefix() + productId;
        String jsonStr = redisService.get(redisKey);
        // 缓存命中
        if (StringUtils.isNotBlank(jsonStr)) {
            return JSON.parseObject(jsonStr, ProductDTO.class);
        }
        // 查询数据库
        ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(productId);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDepartures(productDepartureService.listProductDepartureByProductId(productId));
        productDTO.setImageUrls(productImageService.listProductImageUrlByProductId(productId));
        BeanUtils.copyProperties(productInfoDTO, productDTO);
        redisService.set(redisKey, JSON.toJSONString(productDTO), 24, TimeUnit.HOURS);
        return productDTO;
    }
}
