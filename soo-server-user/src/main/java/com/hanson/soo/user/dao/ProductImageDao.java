package com.hanson.soo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.ProductImageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImageDao extends BaseMapper<ProductImageDO> {
    List<String> listProductImageUrlByProductId(String productId);
}
