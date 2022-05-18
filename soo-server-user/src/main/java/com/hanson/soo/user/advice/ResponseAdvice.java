package com.hanson.soo.user.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.soo.common.response.ResponseData;
import com.hanson.soo.user.handler.MyExceptionHandler;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 全局Controller拦截器
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    private final static Logger logger = LoggerFactory.getLogger(ResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof MyExceptionHandler) {
            logger.error("错误码："+ ((MyExceptionHandler) o).getResponseCode().getCode() + "，错误信息："+ ((MyExceptionHandler) o).getResponseCode().getMessage());
            return ResponseData.fail(((MyExceptionHandler) o).getResponseCode());
        } else if(o instanceof String) {
            return objectMapper.writeValueAsString(ResponseData.success(o));
        }
        return ResponseData.success(o);
    }
}