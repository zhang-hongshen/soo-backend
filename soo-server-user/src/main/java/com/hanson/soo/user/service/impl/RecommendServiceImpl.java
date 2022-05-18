package com.hanson.soo.user.service.impl;

import com.hanson.soo.user.service.RecommendService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static Logger logger = LogManager.getLogger(RecommendService.class);

    // 请求url
    private final String url = "http://127.0.0.1:5000/predict";

    public List<String> predict(String userId){
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("user_id",userId);
        logger.info("接口调用中...");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap);
        ResponseEntity<List<String>> result  = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );
        logger.info("接口调用成功!");
        return result.getBody();
    }
}
