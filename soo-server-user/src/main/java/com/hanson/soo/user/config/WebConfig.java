package com.hanson.soo.user.config;

import com.hanson.soo.user.interceptor.TokenAuthorizationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenAuthorizationInterceptor tokenAuthorizationInterceptor;

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    /**
     * token验证拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("添加token验证拦截器");
        registry.addInterceptor(tokenAuthorizationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login", "/api/user/register", "/api/user/phone/validate",
                        "/api/product/info", "/api/product/detail",
                        "/api/city");
    }

}