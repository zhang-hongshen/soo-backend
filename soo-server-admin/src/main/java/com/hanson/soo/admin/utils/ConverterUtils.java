package com.hanson.soo.admin.utils;

import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtils {
    public static UserDTO userDO2DTO(UserInfoDO userInfoDO){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfoDO,userDTO);
        return userDTO;
    }

    public static UserInfoDO userDTO2DO(UserDTO userDTO){
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(userDTO, userInfoDO);
        return userInfoDO;
    }

    public static ProductInfoDO productInfoDTO2DO(ProductInfoDTO productInfoDTO){
        ProductInfoDO productInfoDO = new ProductInfoDO();
        BeanUtils.copyProperties(productInfoDTO, productInfoDO);
        return productInfoDO;
    }

    public static ProductInfoDTO productInfoDO2DTO(ProductInfoDO productInfoDO){
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfoDO, productInfoDTO);
        return productInfoDTO;
    }

    public static List<ProductImageDO> productDetailDTO2imageDOList(ProductDetailDTO productDetailDTO){
        List<ProductImageDO> productImageDOs = new ArrayList<>();
        String productId = productDetailDTO.getProductId();
        productDetailDTO.getImageUrls().forEach((url) ->{
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(url);
            productImageDO.setStatus(Boolean.FALSE);
            productImageDOs.add(productImageDO);
        });
        return productImageDOs;
    }
}
