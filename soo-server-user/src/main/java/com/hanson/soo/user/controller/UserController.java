package com.hanson.soo.user.controller;


import com.hanson.soo.user.pojo.dto.ProductInfoDTO;
import com.hanson.soo.user.pojo.dto.UserDTO;
import com.hanson.soo.user.service.RecommendService;
import com.hanson.soo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private RecommendService recommendService;

    @GetMapping("/search")
    public String search(@RequestParam("current") int current,
                         @RequestParam("pageSize") int pageSize,
                         @RequestParam("userId") String userId,
                         @ModelAttribute ProductInfoDTO productInfoDTO){
        System.out.println("UserController.search()");
        return "UserController.search()";
    }

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("password") String password){
        System.out.println("UserController.login()");
        System.out.println("{phone:"+phone+",password:"+password+"}");
        return "UserController.login()";
    }

    @PostMapping("/register")
    public int register(UserDTO userDTO){
        System.out.println("UserController.register()");
        System.out.println(userDTO);
        return userService.insertUser(userDTO);
    }

    //备用代码
    @PostMapping
    public String query(@RequestParam("userId") String userId){
        String url = "http://127.0.0.1:5000/predict";
        System.out.println(userId);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("user_id",userId);
        System.out.println("接口调用中...");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap);
        ResponseEntity<String> result  = restTemplate.exchange(
                url, HttpMethod.POST,
                httpEntity,String.class
        );
        System.out.println("接口调用成功..");
        System.out.println(result);
        return result.getBody();
    }
}
