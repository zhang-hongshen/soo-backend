package com.hanson.soo.user.utils;

import com.hanson.soo.common.pojo.entity.*;
import com.hanson.soo.user.pojo.dto.*;
import com.hanson.soo.user.pojo.vo.*;
import org.springframework.beans.BeanUtils;


public class ConverterUtils {
    public static UserInfoDTO userInfoDO2DTO(UserInfoDO userInfoDO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfoDO, userInfoDTO);
        return userInfoDTO;
    }

    public static UserInfoDO userInfoDTO2DO(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(userInfoDTO, userInfoDO);
        return userInfoDO;
    }

    public static UserBasicInfoVO userInfoDTO2BasicInfoVO(UserInfoDTO userInfoDTO) {
        UserBasicInfoVO userBasicInfoVO = new UserBasicInfoVO();
        BeanUtils.copyProperties(userInfoDTO,userBasicInfoVO);
        return userBasicInfoVO;
    }

    public static UserInfoDTO userBasicInfoVO2InfoDTO(UserBasicInfoVO userBasicInfoVO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userBasicInfoVO,userInfoDTO);
        return userInfoDTO;
    }

    public static ConsigneeDO consigneeDTO2DO(ConsigneeDTO consigneeDTO) {
        ConsigneeDO consigneeDO = new ConsigneeDO();
        BeanUtils.copyProperties(consigneeDTO, consigneeDO);
        return consigneeDO;
    }

    public static ConsigneeDTO consigneeDO2DTO(ConsigneeDO consigneeDO){
        ConsigneeDTO consigneeDTO = new ConsigneeDTO();
        BeanUtils.copyProperties(consigneeDO, consigneeDTO);
        return consigneeDTO;
    }

    public static ConsigneeDTO consigneeVO2DTO(ConsigneeVO consigneeVO) {
        ConsigneeDTO consigneeDTO = new ConsigneeDTO();
        BeanUtils.copyProperties(consigneeVO, consigneeDTO);
        return consigneeDTO;
    }

    public static ConsigneeVO consigneeDTO2VO(ConsigneeDTO consigneeDTO) {
        ConsigneeVO consigneeVO = new ConsigneeVO();
        BeanUtils.copyProperties(consigneeDTO, consigneeVO);
        return consigneeVO;
    }

    public static ProductInfoDO productInfoDTO2DO(ProductInfoDTO productInfoDTO) {
        ProductInfoDO productInfoDO = new ProductInfoDO();
        BeanUtils.copyProperties(productInfoDTO, productInfoDO);
        return productInfoDO;
    }

    public static ProductInfoVO productInfoDTO2VO(ProductInfoDTO productInfoDTO) {
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfoDTO, productInfoVO);
        return productInfoVO;
    }

    public static ProductInfoDTO productInfoDO2DTO(ProductInfoDO productInfoDO) {
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfoDO, productInfoDTO);
        return productInfoDTO;
    }

    public static ProductVO productDTO2VO(ProductDTO productDTO) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productDTO, productVO);
        return productVO;
    }

    public static ChartDTO chartVO2DTO(ChartVO chartVO) {
        ChartDTO chartDTO = new ChartDTO();
        BeanUtils.copyProperties(chartVO, chartDTO);
        return chartDTO;
    }

    public static ChartVO chartDTO2VO(ChartDTO chartDTO) {
        ChartVO chartVO = new ChartVO();
        BeanUtils.copyProperties(chartDTO, chartVO);
        return chartVO;
    }
    public static ChartDO chartDTO2DO(ChartDTO chartDTO) {
        ChartDO chartDO = new ChartDO();
        BeanUtils.copyProperties(chartDTO, chartDO);
        return chartDO;
    }

    public static ChartDTO chartDO2DTO(ChartDO chartDO) {
        ChartDTO chartDTO = new ChartDTO();
        BeanUtils.copyProperties(chartDO, chartDTO);
        return chartDTO;
    }

    public static OrderDetailDO orderDetailDTO2DO(OrderDetailDTO orderDetailDTO) {
        OrderDetailDO orderDetailDO = new OrderDetailDO();
        BeanUtils.copyProperties(orderDetailDTO, orderDetailDO);
        return orderDetailDO;
    }

    public static OrderDetailDTO orderDetailDO2DTO(OrderDetailDO orderDetailDO) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        BeanUtils.copyProperties(orderDetailDO, orderDetailDTO);
        return orderDetailDTO;
    }

    public static OrderVO orderDTO2VO(OrderDTO orderDTO) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderDTO, orderVO);
        return orderVO;
    }

    public static CommentDO commentDTO2DO(CommentDTO commentDTO) {
        CommentDO commentDO = new CommentDO();
        BeanUtils.copyProperties(commentDTO, commentDO);
        return commentDO;
    }

    public static CommentDTO commentDO2DTO(CommentDO commentDO) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentDO, commentDTO);
        return commentDTO;
    }

    public static CommentDTO commentVO2DTO(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentVO, commentDTO);
        return commentDTO;
    }

    public static CommentVO commentDTO2VO(CommentDTO commentDTO) {
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(commentDTO, commentVO);
        return commentVO;
    }

    public static CityDTO cityDO2DTO(CityDO cityDO) {
        CityDTO cityDTO = new CityDTO();
        BeanUtils.copyProperties(cityDO, cityDTO);
        return cityDTO;
    }
}
