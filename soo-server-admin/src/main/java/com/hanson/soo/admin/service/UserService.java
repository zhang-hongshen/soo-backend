package com.hanson.soo.admin.service;


import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserInfoDTO;

import java.util.List;

public interface UserService {
    PageListDTO<List<UserInfoDTO>> listUser(int current, int pageSize, UserInfoDTO query);
}
