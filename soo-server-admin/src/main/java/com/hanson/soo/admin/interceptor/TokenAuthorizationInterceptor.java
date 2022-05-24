package com.hanson.soo.admin.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hanson.soo.admin.exception.TokenAuthorizationException;
import com.hanson.soo.admin.pojo.RedisKeyPrefix;
import com.hanson.soo.admin.service.AdminService;
import com.hanson.soo.common.service.RedisService;
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
    private AdminService adminService;
    @Autowired
    private RedisService redisService;

    private static Logger logger = LoggerFactory.getLogger(TokenAuthorizationInterceptor.class);

    /**
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return
     * @throws TokenAuthorizationException 权限认证异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws TokenAuthorizationException{
        logger.info("请求接口：" + request.getRequestURI());
        String token = request.getHeader("Authorization");
        // 前端token异常
        if (StringUtils.isBlank(token)) {
            throw new TokenAuthorizationException();
        }
        String adminId = adminService.getAdminIdByToken(token);
        // token还未过期
        if (redisService.exists(RedisKeyPrefix.ADMIN_TOKEN.getPrefix() + token)) {
            logger.info("token验证成功");
        } else {
            // token异常
            if (StringUtils.isBlank(adminId)) {
                logger.info("token不存在");
                throw new TokenAuthorizationException();
            }
            // 用户超过2小时未操作登出
            if (ChronoUnit.MINUTES.between(adminService.getTokenUpdateTimeByAdminId(adminId), LocalDateTime.now()) >= 120) {
                logger.info("用户长时间位操作登出");
                throw new TokenAuthorizationException();
            }
            response.setHeader("Authorization", adminService.refreshTokenByAdminId(adminId));
        }
        request.setAttribute("adminId", adminId);
        // token续签
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
