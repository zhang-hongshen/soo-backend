package com.hanson.soo.user.service.impl.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.common.service.RedisService;
import com.hanson.soo.user.exception.TokenAuthorizationException;
import com.hanson.soo.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TokenAuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    private final String REDIS_KEY_PREFIX = "soo:user:token";

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("TokenAuthorizationInterceptor.preHandle");
        String token = request.getHeader("Authorization");
        logger.info("token值为：" + token);
        // 前端token异常
        if (StringUtils.isBlank(token)) {
            throw new TokenAuthorizationException();
        }
        // token还未过期
        if (redisService.exists(REDIS_KEY_PREFIX + ":" + token)) {
            logger.info("token验证成功");
            return true;
        }
        String userId = userService.getUserIdByToken(token);
        // token异常
        if (StringUtils.isBlank(userId)) {
            logger.info("token不存在");
            throw new TokenAuthorizationException();
        }
        // 用户超过2小时未操作登出
        if (ChronoUnit.MINUTES.between(userService.getTokenUpdateTimeByUserId(userId), LocalDateTime.now()) >= 120) {
            logger.info("用户长时间位操作登出");
            throw new TokenAuthorizationException();
        }
        // token续签
        response.setHeader("Authorization", userService.refreshTokenByUserId(userId));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("TokenAuthorizationInterceptor.postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("TokenAuthorizationInterceptor.afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
