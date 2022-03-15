package com.hanson.soo.admin.controller;


import com.hanson.soo.admin.pojo.dto.AdminDTO;
import com.hanson.soo.admin.pojo.dto.TokenDTO;
import com.hanson.soo.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    public AdminService adminService;

    @GetMapping("/login")
    public TokenDTO login(){
        return new TokenDTO("admin-token");
    }

    @GetMapping("/info")
    public AdminDTO login(@RequestParam("token")String token){
        List<String> roles = new ArrayList<>(){{add("admin");}};
        String intrdocution = "I am a super administrator";
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        String name = "Super Admin";
        AdminDTO adminDTO = new AdminDTO(roles,intrdocution,avatar,name);
        return adminDTO;
    }

    @PostMapping("/logout")
    public String logout(){
        return "success";
    }
}
