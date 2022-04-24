package com.hanson.soo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.CityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityDao extends BaseMapper<CityDO> {
    List<String> listCityName();
}
