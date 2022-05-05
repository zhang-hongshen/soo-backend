package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.dto.UserInfoDTO;
import com.hanson.soo.user.pojo.vo.UserBasicInfoVO;
import com.hanson.soo.user.service.UserService;
import com.hanson.soo.user.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserInfoDTO userInfoDTO){
        return userService.getToken(userInfoDTO);
    }

    @GetMapping("/info")
    public UserInfoDTO getInfo(@RequestHeader("Authorization") String token){
        return userService.getUserInfoByUserId(userService.getUserIdByToken(token));
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfoDTO userInfoDTO){
        return userService.insertUser(userInfoDTO);
    }

    @PostMapping("/phone/validate")
    public boolean validatePhone(@RequestBody String phone){
        return userService.validatePhone(phone);
    }

    @GetMapping("/logout")
    public boolean logout(@RequestHeader("token")String token){
        return userService.deleteUserToken(token);
    }

    @GetMapping("/location")
    public String getLocation(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        return "北京";
    }

    @GetMapping("/basicinfo/{userId}")
    public UserBasicInfoVO getBasicInfo(@PathVariable("userId")String userId){
        UserInfoDTO userInfoDTO = userService.getUserInfoByUserId(userId);
        return ConverterUtils.userInfoDTO2BasicInfoVO(userInfoDTO);
    }

    @PostMapping("/basicinfo/update/{userId}")
    public boolean updateBasicInfoByUserId(@PathVariable("userId")String userId,
                                  @RequestBody UserBasicInfoVO userBasicInfoVO){
        userService.updateBasicInfoByUserId(userId, ConverterUtils.userBasicInfoVO2InfoDTO(userBasicInfoVO));
        return true;
    }

    @GetMapping("/password/validate/{userId}")
    public boolean validatePasswordByUserId(@PathVariable("userId")String userId,
                                            @RequestBody String password){
        String realPassword = userService.getPasswordByUserId(userId);
        return realPassword.equals(password);
    }


    @PostMapping("/password/update/{userId}")
    public boolean changePassword(@PathVariable("userId")String userId,
                                  @RequestBody String password){
        return userService.updatePasswordByUserId(userId, password);
    }

}
