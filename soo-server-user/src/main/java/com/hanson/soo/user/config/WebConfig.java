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
                .addPathPatterns("/api/user/info", "/api/user/basicinfo/**", "/api/user/logout",
                                    "/api/user/password/**",
                                    "/api/product/predict",
                                    "/api/comment/**",
                                    "/api/city/**",
                                    "/api/order/**",
                                    "/api/cart/**",
                                    "/api/consignee/**")
                .excludePathPatterns("/api/comment/query");
    }

}