package com.hanson.soo.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanson.soo.common.pojo.entity.UserTokenDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenDO> {
    void insertOrUpdateTokenByUserId(String userId, String token);
    String getUserIdByToken(String token);
    LocalDateTime getUpdateTimeByUserId(String userId);
}
