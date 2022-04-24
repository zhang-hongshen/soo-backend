package com.hanson.soo.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;
import com.hanson.soo.user.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    final String REDIS_KEY_PREFIX = "soo:product:";

    @Override
    public PageDTO<List<ProductInfoDTO>> listProductInfos(int current, int pageSize, ProductQO query) {
        return productInfoService.listProductInfo(current, pageSize, query);
    }

    @Override
    public List<ProductInfoDTO> predict(String userId) {
        List<String> productIds = recommendService.predict(userId);
        return productInfoService.listProductInfoByProductId(productIds);
    }

    @Override
    public ProductDTO getProductByProductId(String productId) {
        //查询缓存
        final String redisKey = REDIS_KEY_PREFIX + productId;
        String jsonStr = redisService.get(redisKey);
        if (StringUtils.isNotBlank(jsonStr)) {
            return JSON.parseObject(jsonStr, ProductDTO.class);
        }
        ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(productId);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDepartures(productDepartureService.listProductDepartureByProductId(productId));
        productDTO.setImageUrls(productImageService.listProductImageUrlByProductId(productId));
        BeanUtils.copyProperties(productInfoDTO, productDTO);
        redisService.set(redisKey, JSON.toJSONString(productDTO), 24, TimeUnit.HOURS);
        return productDTO;
    }
}
