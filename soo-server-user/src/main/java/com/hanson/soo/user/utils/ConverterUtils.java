package com.hanson.soo.user.utils;

import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.dto.UserDTO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.common.pojo.entity.UserDO;
import org.springframework.beans.BeanUtils;


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

}
