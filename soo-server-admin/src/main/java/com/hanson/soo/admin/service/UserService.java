package com.hanson.soo.admin.service;


import com.hanson.soo.admin.pojo.dto.UserInfoDTO;
import com.hanson.soo.admin.pojo.qo.UserQO;
import com.hanson.soo.common.pojo.dto.PageDTO;

import java.util.List;

public interface UserService {
    PageDTO<List<UserInfoDTO>> listUser(int current, int pageSize, UserQO userQO);
}
