package com.hanson.soo.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.AdminTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTokenDao extends BaseMapper<AdminTokenDO> {
    String getAdminIdByToken(String token);
    void insertOrUpdateTokenByAdminId(String adminId, String token);
}
