package com.hanson.soo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.ProductDepartureDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDepartureDao extends BaseMapper<ProductDepartureDO> {
    List<String> listProductDepartureByProductId(String productId);
}
