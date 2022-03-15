package com.hanson.soo.admin.controller;

import com.hanson.soo.common.pojo.dto.PageListDTO;
import com.hanson.soo.admin.pojo.dto.UserDTO;
import com.hanson.soo.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public PageListDTO<List<UserDTO>> query(@RequestParam("current") int current,
                                            @RequestParam("pageSize")int pageSize,
                                            @ModelAttribute UserDTO userDTO){
        System.out.println("UserController.listUsers()");
        System.out.println("{current:"+current+",pageSize:"+pageSize+"}");
        System.out.println(userDTO);
        PageListDTO<List<UserDTO>> res = userService.listUsers(current, pageSize, userDTO);
        return res;
    }
}
