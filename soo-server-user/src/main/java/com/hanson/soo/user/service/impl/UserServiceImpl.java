package com.hanson.soo.user.service.impl;

import com.hanson.soo.common.pojo.entity.UserDO;
import com.hanson.soo.common.dao.UserDao;
import com.hanson.soo.user.pojo.dto.UserDTO;
import com.hanson.soo.user.service.UserService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(UserDTO userDTO) {
        UserDO userDO = ConverterUtils.userDTO2DO(userDTO);
        String userId = String.valueOf(UUID.randomUUID());
        userDO.setUserId(userId);
        return userDao.insert(userDO);
    }
}
