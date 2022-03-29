package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.vo.UserInfoVO;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserInfoDTO;
import com.hanson.soo.admin.service.UserService;
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
    public PageListDTO<List<UserInfoVO>> query(@RequestParam("current") int current,
                                               @RequestParam("pageSize")int pageSize,
                                               @RequestBody UserInfoDTO query){
        PageListDTO<List<UserInfoDTO>> pageListDTO = userService.listUser(current, pageSize, query);
        List<UserInfoVO> userInfoVOs = new ArrayList<>();
        for(UserInfoDTO userInfoDTO : pageListDTO.getList()){
            userInfoVOs.add(ConverterUtils.userDTO2VO(userInfoDTO));
        }
        return new PageListDTO<>(userInfoVOs, pageListDTO.getTotal());
    }
}
