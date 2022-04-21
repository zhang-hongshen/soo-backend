package com.hanson.soo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.CartDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao extends BaseMapper<CartDO> {
}
