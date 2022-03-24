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

    @PostMapping("/login")
    public TokenDTO login(){
        return new TokenDTO("admin-token");
    }

    @GetMapping("/info")
    public AdminDTO login(@RequestParam("token")String token){
        List<String> roles = new ArrayList<>(){{add("admin");}};
        String intrdocution = "I am a super administrator";
        String avatar = "https://www.google.com/search?q=%E7%94%A8%E6%88%B7%E5%A4%B4%E5%83%8F&sxsrf=APq-WBtMGKycHfysHB0IJz4sCNL2lOp-lw:1647395827147&tbm=isch&source=iu&ictx=1&vet=1&fir=P6eONKO025XIsM%252CJM25QWeIkEJrRM%252C_%253BYRMqckUVxc_KuM%252CbV7X-1A5d7acKM%252C_%253BMEk2nKY1KhWL9M%252CbV7X-1A5d7acKM%252C_%253BxGTADrQV8N5BoM%252CbV7X-1A5d7acKM%252C_%253BUXPusVG6CZ_m5M%252CAkLB_G8ZFpq7PM%252C_%253B8s-4awQq4iutpM%252CTNcELx2-TEUYAM%252C_%253Bxio92YhTmN_qfM%252CAkLB_G8ZFpq7PM%252C_%253BJizAk0dDDyGv3M%252CCp-GkFXg2qMCTM%252C_&usg=AI4_-kTuHqdCQhBMfvpW0S9a4XA2fFdpxA&sa=X&ved=2ahUKEwjsitPYw8n2AhXTdHAKHWgyBXQQ9QF6BAgPEAE#imgrc=8s-4awQq4iutpM";
        String name = "Super Admin";
        AdminDTO adminDTO = new AdminDTO(roles,intrdocution,avatar,name);
        return adminDTO;
    }

    @PostMapping("/logout")
    public String logout(){
        return "success";
    }
}
