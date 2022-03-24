package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanson.soo.common.dao.ProductDepartureDao;
import com.hanson.soo.common.dao.ProductImageDao;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.user.pojo.dto.ProductDetailDTO;
import com.hanson.soo.user.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;

    public ProductDetailDTO getProductDetailByProductId(String productId){
        List<ProductImageDO> productImageDOs = productImageDao.selectList(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productId));
        List<ProductDepartureDO> productDepartureDOs = productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .eq(ProductDepartureDO::getProductId, productId));
        List<String> imageUrls = new ArrayList<>();
        List<String> departures = new ArrayList<>();
        productImageDOs.forEach((productImageDO) -> imageUrls.add(productImageDO.getUrl()));
        productDepartureDOs.forEach((productDepartureDO) -> departures.add(productDepartureDO.getDeparture()));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setImageUrls(imageUrls);
        productDetailDTO.setDepartures(departures);
        return productDetailDTO;
    }
}
