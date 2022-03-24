package com.hanson.soo.user.service.impl;

import com.hanson.soo.user.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RestTemplate restTemplate;

    public List<String> predict(String userId){
        String url = "http://127.0.0.1:5000/predict";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("user_id",userId);
        System.out.println(userId);
        System.out.println("接口调用中...");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap);
        ResponseEntity<List<String>> result  = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        System.out.println("接口调用成功..");
        System.out.println(result);
        System.out.println(result.getBody());
        return result.getBody();
    }
}
