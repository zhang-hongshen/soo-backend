package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.admin.dao.ProductDepartureDao;
import com.hanson.soo.admin.dao.ProductImageDao;
import com.hanson.soo.admin.dao.ProductInfoDao;
import com.hanson.soo.admin.pojo.dto.ProductDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.qo.ProductQO;
import com.hanson.soo.admin.service.ProductService;
import com.hanson.soo.admin.utils.AliyunOSSUtils;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.ProductState;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.common.utils.UUIDUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductDepartureDao productDepartureDao;
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY_PREFIX = "soo:product";

    @Transactional(readOnly = true)
    @Override
    public PageDTO<List<ProductInfoDTO>> listProductInfo(int current, int pageSize, ProductQO productQO) {
        List<ProductInfoDO> productInfoDOs = productInfoDao.selectList(new LambdaQueryWrapper<ProductInfoDO>()
                .eq(!productQO.getStatus().equals(ProductState.ALL.getValue()),
                        ProductInfoDO::getState, ProductState.getStateByValue(productQO.getStatus()))
                .like(StringUtils.isNotBlank(productQO.getProductName()), ProductInfoDO::getProductName, productQO.getProductName())
                .like(StringUtils.isNotBlank(productQO.getDestination()), ProductInfoDO::getDestination, productQO.getDestination()));
        Set<String> productIds = new HashSet<>();
        productInfoDOs.forEach((productInfoDO) -> productIds.add(productInfoDO.getProductId()));
        List<ProductDepartureDO> productDepartureDOs = productDepartureDao.selectList(new LambdaQueryWrapper<ProductDepartureDO>()
                .like(StringUtils.isNotBlank(productQO.getDeparture()), ProductDepartureDO::getDeparture, productQO.getDeparture()));
        Set<String> productIdsByDeparture = new HashSet<>();
        productDepartureDOs.forEach((productDepartureDO) -> productIdsByDeparture.add(productDepartureDO.getProductId()));
        //取交集
        productIds.retainAll(productIdsByDeparture);
        if(productIds.isEmpty()){
            return new PageDTO<>(new ArrayList<>(), 0);
        }
        IPage<ProductInfoDO> page = productInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds));
        List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();
        for (ProductInfoDO productInfoDO : page.getRecords()) {
            productInfoDTOs.add(ConverterUtils.productInfoDO2DTO(productInfoDO));
        }
        return new PageDTO<>(productInfoDTOs, (int) page.getTotal());
    }

    @Transactional
    @Override
    public boolean insert(ProductDTO productDTO) {
        String productId = UUIDUtils.getId();
        ProductInfoDO productInfoDO = new ProductInfoDO();
        BeanUtils.copyProperties(productDTO, productInfoDO);
        productInfoDO.setProductId(productId);
        productInfoDao.insert(productInfoDO);
        productDTO.getDepartures().forEach(departure->{
            ProductDepartureDO productDepartureDO = new ProductDepartureDO();
            productDepartureDO.setProductId(productId);
            productDepartureDO.setDeparture(departure);
            productDepartureDao.insert(productDepartureDO);
        });
        productDTO.getImageUrls().forEach(imageUrl->{
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(imageUrl);
            productImageDao.insert(productImageDO);
        });
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProductByProductId(String productId) {
        ProductInfoDO productInfoDO = productInfoDao.selectOne(new LambdaQueryWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getProductId, productId));
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productInfoDO, productDTO);
        productDTO.setDepartures(productDepartureDao.listDepartureByProductId(productId));
        productDTO.setImageUrls(productImageDao.listImageUrlByProductIdAndStatus(productId, Boolean.TRUE));
        return productDTO;
    }

    @SneakyThrows
    @Transactional
    @Override
    public boolean updateProductByProductId(ProductDTO productDTO){
        String productId = productDTO.getProductId();
        // Redis第一次删除
        redisService.delete(REDIS_KEY_PREFIX + ":" +productId);
        // 开始更新MySQL
        ProductInfoDO productInfoDO = new ProductInfoDO();
        BeanUtils.copyProperties(productDTO, productInfoDO);
        productInfoDao.update(productInfoDO, new LambdaUpdateWrapper<ProductInfoDO>()
                .eq(ProductInfoDO::getProductId, productId));
        productDepartureDao.delete(new LambdaUpdateWrapper<ProductDepartureDO>()
                .eq(ProductDepartureDO::getProductId, productId));
        productDTO.getDepartures().forEach( departure -> {
            ProductDepartureDO productDepartureDO = new ProductDepartureDO();
            productDepartureDO.setProductId(productId);
            productDepartureDO.setDeparture(departure);
            productDepartureDao.insert(productDepartureDO);
        });
        //更新图片
        Set<ProductImageDO> newProductImageDOs = new HashSet<>();
        productDTO.getImageUrls().forEach((url) -> {
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(url);
            productImageDO.setStatus(Boolean.TRUE);
            newProductImageDOs.add(productImageDO);
        });
        Set<ProductImageDO> oldProductImageDOs = new HashSet<>(productImageDao.selectList(new LambdaQueryWrapper<ProductImageDO>()
                .eq(ProductImageDO::getProductId, productId)
                .eq(ProductImageDO::getStatus, Boolean.TRUE)));
        //求交集
        Set<ProductImageDO> intersection = new HashSet<>(oldProductImageDOs);
        intersection.retainAll(newProductImageDOs);
        //求差集
        oldProductImageDOs.removeAll(intersection);
        newProductImageDOs.removeAll(intersection);
        /*
          插入新图片
         */
        for (ProductImageDO productImageDO : newProductImageDOs) {
            productImageDO.setStatus(Boolean.TRUE);
            productImageDao.insert(productImageDO);
        }
        /*
          旧图片设置为历史图片
         */
        for (ProductImageDO productImageDO : oldProductImageDOs) {
            productImageDO.setStatus(Boolean.FALSE);
            productImageDao.update(productImageDO, new LambdaUpdateWrapper<ProductImageDO>()
                    .eq(ProductImageDO::getProductId, productId)
                    .eq(ProductImageDO::getUrl, productImageDO.getUrl()));
        }
        Thread.sleep(500);
        // Redis第一次删除
        redisService.delete(REDIS_KEY_PREFIX + ":" +productId);
        return true;
    }

    @SneakyThrows
    @Transactional
    @Override
    public boolean deleteByProductIds(List<String> productIds) {
        List<String> redisKeys = productIds.stream()
                .map(productId -> REDIS_KEY_PREFIX + ":" +productId)
                .collect(Collectors.toList());
        // 第一次删除
        redisService.delete(redisKeys);
        // 更新MySQL
        productInfoDao.delete(new LambdaUpdateWrapper<ProductInfoDO>()
                .in(ProductInfoDO::getProductId, productIds));
        productImageDao.delete(new LambdaUpdateWrapper<ProductImageDO>()
                .in(ProductImageDO::getProductId, productIds));
        productDepartureDao.delete(new LambdaUpdateWrapper<ProductDepartureDO>()
                .in(ProductDepartureDO::getProductId, productIds));
        Thread.sleep(500);
        // 第二次删除
        redisService.delete(redisKeys);
        // 删除阿里云OSS存储的图片
        productIds.forEach(productId ->
                productImageDao.listImageUrlByProductId(productId).forEach(AliyunOSSUtils::deleteObjectByUrl));
        return true;
    }
}
