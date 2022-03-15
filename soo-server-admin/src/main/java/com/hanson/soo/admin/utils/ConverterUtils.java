package com.hanson.soo.admin.utils;

import com.hanson.soo.admin.pojo.dto.ProductDetailDTO;
import com.hanson.soo.admin.pojo.dto.ProductInfoDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.common.pojo.entity.UserDO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConverterUtils {
    public static UserDTO userDO2DTO(UserDO userDO){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO,userDTO);
        return userDTO;
    }

    public static UserDO userDTO2DO(UserDTO userDTO){
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        return userDO;
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
        List<ProductImageDO> res = new ArrayList<>();
        String productId = productDetailDTO.getProductId();
        for(String url : productDetailDTO.getImageUrls()){
            ProductImageDO productImageDO = new ProductImageDO();
            productImageDO.setProductId(productId);
            productImageDO.setUrl(url);
            res.add(productImageDO);
        }
        return res;
    }
}
