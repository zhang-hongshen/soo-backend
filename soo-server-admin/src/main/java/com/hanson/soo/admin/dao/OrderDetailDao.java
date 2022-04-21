package com.hanson.soo.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.OrderDetailDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetailDO> {
}
