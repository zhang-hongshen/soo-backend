package com.hanson.soo.user.service.impl;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.pojo.dto.ProductDTO;
import com.hanson.soo.user.pojo.dto.ProductDetailDTO;
import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.qo.ProductQO;
import com.hanson.soo.user.service.ProductDetailService;
import com.hanson.soo.user.service.ProductInfoService;
import com.hanson.soo.user.service.ProductService;
import com.hanson.soo.user.service.RecommendService;
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
    private ProductDetailService productDetailService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RecommendService recommendService;

    @Override
    public PageListDTO<List<ProductInfoDTO>> listInfo(int current, int pageSize, ProductQO query) {
        return productInfoService.listInfo(current, pageSize, query);
    }

    @Override
    public List<ProductInfoDTO> predict(String userId) {
        List<String> productIds = recommendService.predict(userId);
        return productInfoService.listInfoByProductId(productIds);
    }

    @Override
    public ProductDTO getByProductId(String productId) {
        String key = "product:" + productId;
        ProductDTO productDTO = (ProductDTO) redisService.get(key);
        if (productDTO == null) {
            ProductDetailDTO productDetailDTO = productDetailService.getProductDetailByProductId(productId);
            ProductInfoDTO productInfoDTO = productInfoService.getByProductId(productId);
            productDTO = new ProductDTO();
            BeanUtils.copyProperties(productDetailDTO, productDTO);
            BeanUtils.copyProperties(productInfoDTO, productDTO);
            redisService.set(key, productDTO, 5, TimeUnit.HOURS);
        }
        return productDTO;
    }
}
