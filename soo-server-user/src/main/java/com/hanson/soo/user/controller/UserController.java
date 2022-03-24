package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.dto.UserInfoDTO;
import com.hanson.soo.user.pojo.vo.UserBasicInfoVO;
import com.hanson.soo.user.pojo.vo.UserRegisterVO;
import com.hanson.soo.user.service.UserInfoService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/login")
    public String login(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.getToken(userInfoDTO);
    }

    @GetMapping("/info")
    public UserInfoDTO login(@RequestParam("token") String token){
        System.out.println(token);
        return userInfoService.getUserInfoByToken(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterVO userRegisterVO){
        System.out.println("UserController.register()");
        System.out.println(userRegisterVO);
        return userInfoService.insertUser(ConverterUtils.userRegisterVO2InfoDTO(userRegisterVO));
    }


    @PostMapping("/phone/validate")
    public boolean checkPhone(@RequestBody String phone){
        System.out.println(phone);
        return userInfoService.checkPhone(phone);
    }

    @GetMapping("/logout")
    public boolean logout(){
        System.out.println("UserController.logout()");
        return true;
    }


    @GetMapping("/location")
    public String getLocation(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        return "北京";
    }

    @GetMapping("/basicinfo")
    public UserBasicInfoVO query(@RequestParam("userId")String userId){
        UserInfoDTO userInfoDTO = userInfoService.getUserInfoByUserId(userId);
        return ConverterUtils.userInfoDTO2BasicInfoVO(userInfoDTO);
    }

    @GetMapping("/password/validate")
    public boolean checkPasswordByUserId(@RequestParam("userId")String userId,
                                         @RequestParam("password")String password){
        String realPassword = userInfoService.getPasswordByUserId(userId);
        return realPassword.equals(password);
    }

}
