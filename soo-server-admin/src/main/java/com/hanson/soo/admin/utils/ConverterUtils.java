package com.hanson.soo.admin.utils;

import com.hanson.soo.admin.pojo.dto.*;
import com.hanson.soo.admin.pojo.qo.OrderInfoQO;
import com.hanson.soo.admin.pojo.vo.AdminVO;
import com.hanson.soo.admin.pojo.vo.OrderInfoVO;
import com.hanson.soo.admin.pojo.vo.ProductVO;
import com.hanson.soo.admin.pojo.vo.UserInfoVO;
import com.hanson.soo.common.pojo.entity.AdminDO;
import com.hanson.soo.common.pojo.entity.OrderInfoDO;
import com.hanson.soo.common.pojo.entity.ProductInfoDO;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;

public class ConverterUtils {

    public static AdminDTO adminDO2DTO(AdminDO adminDO) {
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(adminDO, adminDTO);
        return adminDTO;
    }

    public static AdminVO adminDTO2VO(AdminDTO adminDTO) {
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(adminDTO, adminVO);
        return adminVO;
    }

    public static UserInfoDTO userDO2DTO(UserInfoDO userInfoDO){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfoDO, userInfoDTO);
        return userInfoDTO;
    }

    public static UserInfoDO userDTO2DO(UserInfoDTO userInfoDTO){
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(userInfoDTO, userInfoDO);
        return userInfoDO;
    }

    public static UserInfoVO userDTO2VO(UserInfoDTO userInfoDTO){
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfoDTO, userInfoVO);
        return userInfoVO;
    }

    public static ProductVO productDTO2VO(ProductDTO productDTO) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productDTO, productVO);
        return  productVO;
    }

    public static ProductDTO productVO2DTO(ProductVO productVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productVO, productDTO);
        return  productDTO;
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

    public static OrderInfoDTO orderInfoDO2DTO(OrderInfoDO orderInfoDO){
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtils.copyProperties(orderInfoDO, orderInfoDTO);
        return orderInfoDTO;
    }

    public static OrderInfoVO orderInfoDTO2VO(OrderInfoDTO orderInfoDTO){
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        BeanUtils.copyProperties(orderInfoDTO, orderInfoVO);
        return orderInfoVO;
    }

    public static OrderInfoDTO orderInfoVO2DTO(OrderInfoVO orderInfoVO){
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtils.copyProperties(orderInfoVO, orderInfoDTO);
        return orderInfoDTO;
    }

    public static OrderInfoDTO orderInfoQO2DTO(OrderInfoQO orderInfoQO){
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtils.copyProperties(orderInfoQO, orderInfoDTO);
        return orderInfoDTO;
    }
}
