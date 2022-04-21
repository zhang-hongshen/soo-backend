package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.qo.UserQO;
import com.hanson.soo.admin.pojo.vo.UserInfoVO;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.admin.pojo.dto.UserInfoDTO;
import com.hanson.soo.admin.service.UserService;
import com.hanson.soo.common.pojo.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/info")
    public PageVO<List<UserInfoVO>> query(@RequestParam("current") int current,
                                          @RequestParam("pageSize")int pageSize,
                                          @RequestBody UserQO userQO) {
        PageDTO<List<UserInfoDTO>> pageDTO = userService.listUser(current, pageSize, userQO);
        List<UserInfoVO> userInfoVOs = new ArrayList<>();
        for (UserInfoDTO userInfoDTO : pageDTO.getList()) {
            userInfoVOs.add(ConverterUtils.userDTO2VO(userInfoDTO));
        }
        return new PageVO<>(userInfoVOs, pageDTO.getTotal());
    }
}
