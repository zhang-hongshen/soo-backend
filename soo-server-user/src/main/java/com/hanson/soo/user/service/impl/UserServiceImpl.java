package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.dao.UserTokenDao;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import com.hanson.soo.user.dao.UserInfoDao;
import com.hanson.soo.common.pojo.entity.UserTokenDO;
import com.hanson.soo.user.pojo.dto.UserInfoDTO;
import com.hanson.soo.user.service.UserService;
import com.hanson.soo.user.utils.ConverterUtils;
import com.hanson.soo.user.utils.TokenUtils;
import com.hanson.soo.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserTokenDao userTokenDao;
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY_PREFIX = "soo:user";

    @Override
    @Transactional
    public String insertUser(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = ConverterUtils.userInfoDTO2DO(userInfoDTO);
        String userId = UUIDUtils.getId();
        userInfoDO.setUserId(userId);
        String token = TokenUtils.createToken();
        userInfoDao.insert(userInfoDO);
        UserTokenDO userTokenDO = new UserTokenDO();
        userTokenDO.setToken(token);
        userTokenDO.setUserId(userId);
        userTokenDao.insert(userTokenDO);
        return token;
    }

    @Override
    public boolean validatePhone(String phone) {
        return userInfoDao.selectCount(new LambdaQueryWrapper<UserInfoDO>().eq(UserInfoDO::getPhone, phone)) > 0;
    }

    @Override
    public boolean validateToken(String token) {
        return userTokenDao.selectOne(new LambdaQueryWrapper<UserTokenDO>()
                .eq(UserTokenDO::getToken, token)) != null;
    }

    @Override
    public String refreshTokenByUserId(String userId) {
        String token = TokenUtils.createToken();
        // 更新或插入token
        userTokenDao.insertOrUpdateTokenByUserId(userId, token);
        return token;
    }

    @Override
    @Transactional
    public String getToken(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoDO>()
                .eq(StringUtils.isNotBlank(userInfoDTO.getUsername()), UserInfoDO::getUsername, userInfoDTO.getUsername())
                .eq(UserInfoDO::getPassword,  userInfoDTO.getPassword())
                .eq(StringUtils.isNotBlank(userInfoDTO.getPhone()), UserInfoDO::getPhone, userInfoDTO.getPhone()));
        if (userInfoDO == null) {
            return "";
        }
        return refreshTokenByUserId(userInfoDO.getUserId());
    }

    @Override
    public String getUserIdByToken(String token) {
        return userTokenDao.getUserIdByToken(token);
    }

    @Override
    public UserInfoDTO getUserInfoByToken(String token){
        return getUserInfoByUserId(getUserIdByToken(token));
    }

    @Override
    public UserInfoDTO getUserInfoByUserId(String userId) {
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId));
        return ConverterUtils.userInfoDO2DTO(userInfoDO);
    }

    @Override
    public String getPasswordByUserId(String userId) {
        return userInfoDao.getPasswordByUserId(userId);
    }

    @Override
    public boolean updateBasicInfoByUserId(String userId, UserInfoDTO userInfoDTO) {
       return userInfoDao.update(ConverterUtils.userInfoDTO2DO(userInfoDTO), new LambdaUpdateWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId)) > 0;
    }

    @Override
    public boolean updatePasswordByUserId(String userId, String password) {
        return userInfoDao.update(null, new LambdaUpdateWrapper<UserInfoDO>()
                .set(UserInfoDO::getPassword, password)
                .eq(UserInfoDO::getUserId, userId)) > 0;
    }

    @Override
    public boolean deleteUserToken(String token) {
        return userTokenDao.delete(new LambdaUpdateWrapper<UserTokenDO>()
                .eq(UserTokenDO::getToken, token)) > 0;
    }
}
