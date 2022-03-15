package com.hanson.soo.admin.service;


import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;

import java.util.List;

public interface UserService {
    PageListDTO<List<UserDTO>> listUsers(int current, int pageSize, UserDTO userDTO);
}
