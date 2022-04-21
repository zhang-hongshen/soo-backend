package com.hanson.soo.admin.controller;


import com.hanson.soo.admin.pojo.dto.AdminDTO;
import com.hanson.soo.admin.pojo.vo.AdminVO;
import com.hanson.soo.admin.service.AdminService;
import com.hanson.soo.admin.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    public AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestBody AdminDTO adminDTO){
        return adminService.getToken(adminDTO);
    }

    @GetMapping("/info")
    public AdminVO login(@RequestHeader("Authorization")String token) {
        return ConverterUtils.adminDTO2VO(adminService.getAdminInfoByToken(token));
    }

    @PostMapping("/logout")
    public boolean logout(@RequestHeader("Authorization")String token) {
        return adminService.deleteToken(token);
    }
}
