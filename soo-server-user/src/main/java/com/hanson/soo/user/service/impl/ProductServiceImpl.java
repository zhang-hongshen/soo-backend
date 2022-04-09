package com.hanson.soo.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.pojo.dto.PageListDTO;
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
    public PageListDTO<List<ProductInfoDTO>> listProductInfos(int current, int pageSize, ProductQO query) {
        return productInfoService.listProductInfos(current, pageSize, query);
    }

    @Override
    public List<ProductInfoDTO> predict(String userId) {
        List<String> productIds = recommendService.predict(userId);
        return productInfoService.listProductInfosByProductId(productIds);
    }

    @Override
    public ProductDTO getProductByProductId(String productId) {
        final String redisKey = REDIS_KEY_PREFIX + productId;
        //查询缓存
        String jsonStr = redisService.get(redisKey);
        if (StringUtils.isNotBlank(jsonStr)) {
            return JSON.parseObject(jsonStr, ProductDTO.class);
        }
        List<ProductDepartureDO> productDepartureDOs = productDepartureService.listProductDeparturesByProductId(productId);
        List<ProductImageDO> productImageDOs = productImageService.listProductImagesByProductId(productId);
        ProductInfoDTO productInfoDTO = productInfoService.getProductInfoByProductId(productId);
        List<String> departures = new ArrayList<>();
        List<String> imageUrls = new ArrayList<>();
        productImageDOs.forEach((productImageDO) -> imageUrls.add(productImageDO.getUrl()));
        productDepartureDOs.forEach((productDepartureDO) -> departures.add(productDepartureDO.getDeparture()));
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDepartures(departures);
        productDTO.setImageUrls(imageUrls);
        BeanUtils.copyProperties(productInfoDTO, productDTO);
        redisService.set(redisKey, JSON.toJSONString(productDTO), 2, TimeUnit.HOURS);
        return productDTO;
    }
}
