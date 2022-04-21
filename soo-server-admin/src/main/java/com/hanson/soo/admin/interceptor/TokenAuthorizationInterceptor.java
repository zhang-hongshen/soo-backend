package com.hanson.soo.admin.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.admin.exception.TokenAuthorizationException;
import com.hanson.soo.admin.service.AdminService;
import com.hanson.soo.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenAuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY_PREFIX = "soo:admin:token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("preHandle");
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(redisService.get(REDIS_KEY_PREFIX + ":" + token))) {
            // token还未过期
            return true;
        }
        String adminId = adminService.getAdminIdByToken(token);
        if (StringUtils.isBlank(adminId)) {
            // token异常
            throw new TokenAuthorizationException();
        }
        response.setHeader("Authorization", adminService.refreshTokenByAdminId(adminId));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
