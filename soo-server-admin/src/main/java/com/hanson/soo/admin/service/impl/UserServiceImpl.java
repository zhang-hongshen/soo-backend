package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.UserInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;
import com.hanson.soo.admin.service.UserService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    public PageListDTO<List<UserDTO>> listUsers(int current, int pageSize, UserDTO userDTO) {
        LambdaQueryWrapper<UserInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(userDTO.getUsername()), UserInfoDO::getUsername, userDTO.getUsername());
        Page<UserInfoDO> page = new Page<>(current, pageSize);
        IPage<UserInfoDO> userPage = userInfoDao.selectPage(page, wrapper);
        List<UserInfoDO> userInfoDOS = userPage.getRecords();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserInfoDO userInfoDO : userInfoDOS) {
            userDTOs.add(ConverterUtils.userDO2DTO(userInfoDO));
        }
        return new PageListDTO<>(userDTOs, (int) userPage.getTotal());
    }
}
