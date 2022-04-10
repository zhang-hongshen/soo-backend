package com.hanson.soo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.dao.UserTokenDao;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import com.hanson.soo.common.dao.UserInfoDao;
import com.hanson.soo.common.pojo.entity.UserTokenDO;
import com.hanson.soo.user.pojo.dto.UserInfoDTO;
import com.hanson.soo.user.service.UserInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import com.hanson.soo.user.utils.TokenUtils;
import com.hanson.soo.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserTokenDao userTokenDao;

    @Override
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
    @Transactional(readOnly = true)
    public boolean checkPhone(String phone) {
        return userInfoDao.selectCount(new LambdaQueryWrapper<UserInfoDO>().eq(UserInfoDO::getPhone, phone)) > 0;
    }

    @Override
    @Transactional
    public String getToken(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoDO>()
                .eq(StringUtils.isNotBlank(userInfoDTO.getUsername()), UserInfoDO::getUsername, userInfoDTO.getUsername())
                .eq(UserInfoDO::getPassword,  userInfoDTO.getPassword())
                .eq(StringUtils.isNotBlank(userInfoDTO.getPhone()), UserInfoDO::getPhone, userInfoDTO.getPhone()));
        if (userInfoDO == null) {
            return null;
        }
        String userId = userInfoDO.getUserId();
        UserTokenDO userTokenDO = userTokenDao.selectOne(new LambdaUpdateWrapper<UserTokenDO>()
                .eq(UserTokenDO::getUserId, userId));
        //token不存在，就生成一个token
        if(userTokenDO == null) {
            String token = TokenUtils.createToken();
            UserTokenDO newUserTokenDO = new UserTokenDO();
            newUserTokenDO.setToken(token);
            newUserTokenDO.setUserId(userId);
            userTokenDao.insert(newUserTokenDO);
            return token;
        }
        String token = userTokenDO.getToken();
        if(StringUtils.isBlank(token)) {
            token = TokenUtils.createToken();
            userTokenDO.setToken(token);
            userTokenDao.updateById(userTokenDO);
        }
        return token;
}

    @Override
    @Transactional(readOnly = true)
    public UserInfoDTO getUserInfoByToken(String token){
        UserTokenDO userTokenDO = userTokenDao.selectOne(new LambdaUpdateWrapper<UserTokenDO>()
                .eq(UserTokenDO::getToken, token));
        String userId = userTokenDO.getUserId();
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaUpdateWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId));
        return ConverterUtils.userInfoDO2DTO(userInfoDO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDTO getUserInfoByUserId(String userId) {
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId));
        return ConverterUtils.userInfoDO2DTO(userInfoDO);
    }

    @Override
    @Transactional(readOnly = true)
    public String getPasswordByUserId(String userId) {
        UserInfoDO userInfoDO = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId));
        return userInfoDO.getPassword();
    }

    @Override
    public boolean updateBasicInfoByUserId(String userId, UserInfoDTO userInfoDTO) {
       return userInfoDao.update(ConverterUtils.userInfoDTO2DO(userInfoDTO), new LambdaUpdateWrapper<UserInfoDO>()
                .eq(UserInfoDO::getUserId, userId)) > 0;
    }

    @Transactional
    public boolean updatePasswordByUserId(String userId, String password) {
        return userInfoDao.update(null, new LambdaUpdateWrapper<UserInfoDO>()
                .set(UserInfoDO::getPassword, password)
                .eq(UserInfoDO::getUserId, userId)) > 0;
    }
}
