package com.hanson.soo.user.service;

import java.util.List;

public interface RecommendService {
    List<String> predict(String userId);
}
