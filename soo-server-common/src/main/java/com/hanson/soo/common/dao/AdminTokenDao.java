package com.hanson.soo.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.AdminTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTokenDao extends BaseMapper<AdminTokenDO> {
}
