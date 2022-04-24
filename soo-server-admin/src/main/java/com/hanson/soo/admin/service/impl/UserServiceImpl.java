package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.admin.dao.UserInfoDao;
import com.hanson.soo.admin.pojo.dto.UserInfoDTO;
import com.hanson.soo.admin.pojo.qo.UserQO;
import com.hanson.soo.admin.service.UserService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    @Transactional(readOnly = true)
    public PageDTO<List<UserInfoDTO>> listUser(int current, int pageSize, UserQO userQO) {
        IPage<UserInfoDO> userPage = userInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<UserInfoDO>()
                .like(StringUtils.isNotBlank(userQO.getUsername()), UserInfoDO::getUsername, userQO.getUsername()));
        List<UserInfoDTO> userInfoDTOs = userPage.getRecords().stream()
                .map(ConverterUtils::userDO2DTO)
                .collect(Collectors.toList());
        return new PageDTO<>(userInfoDTOs, (int) userPage.getTotal());
    }
}
