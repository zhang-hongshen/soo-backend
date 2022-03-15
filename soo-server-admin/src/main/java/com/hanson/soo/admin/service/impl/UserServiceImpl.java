package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.UserDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;
import com.hanson.soo.admin.service.UserService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    @Override
    public PageListDTO<List<UserDTO>> listUsers(int current, int pageSize, UserDTO userDTO) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(userDTO.getUsername()),UserDO::getUsername, userDTO.getUsername());
        Page<UserDO> page = new Page<>(current, pageSize);
        IPage<UserDO> userPage = userDao.selectPage(page, wrapper);
        List<UserDO> userDOs = userPage.getRecords();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserDO userDO : userDOs) {
            userDTOs.add(ConverterUtils.userDO2DTO(userDO));
        }
        return new PageListDTO<>(userDTOs, (int) userPage.getTotal());
    }
}
