package com.hanson.soo.admin.config;

import com.hanson.soo.admin.interceptor.TokenAuthorizationInterceptor;
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

    private static Logger logger = LoggerFactory.getLogger(WebConfig.class);
    /**
     * token验证拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("注册token拦截器");
        registry.addInterceptor(tokenAuthorizationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/admin/login", "/api/admin/logout");

    }

}