package com.hanson.soo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanson.soo.common.dao.UserInfoDao;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserInfoDTO;
import com.hanson.soo.admin.service.UserService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.entity.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    @Transactional(readOnly = true)
    public PageListDTO<List<UserInfoDTO>> listUser(int current, int pageSize, UserInfoDTO query) {
        IPage<UserInfoDO> userPage = userInfoDao.selectPage(new Page<>(current, pageSize), new LambdaQueryWrapper<UserInfoDO>()
                .like(StringUtils.isNotBlank(query.getUsername()), UserInfoDO::getUsername, query.getUsername()));
        List<UserInfoDO> userInfoDOs = userPage.getRecords();
        List<UserInfoDTO> userInfoDTOs = new ArrayList<>();
        for (UserInfoDO userInfoDO : userInfoDOs) {
            userInfoDTOs.add(ConverterUtils.userDO2DTO(userInfoDO));
        }
        return new PageListDTO<>(userInfoDTOs, (int) userPage.getTotal());
    }
}
