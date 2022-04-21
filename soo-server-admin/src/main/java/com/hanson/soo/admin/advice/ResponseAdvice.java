package com.hanson.soo.admin.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.soo.admin.exception.TokenAuthorizationException;
import com.hanson.soo.admin.handler.MyExceptionHandler;
import com.hanson.soo.common.response.ResponseCode;
import com.hanson.soo.common.response.ResponseData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.security.InvalidParameterException;


/**
 * 全局Controller拦截器
 */
@RestControllerAdvice
@Order
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof MyExceptionHandler) {
            return ResponseData.fail(((MyExceptionHandler) o).getResponseCode());
        } else if(o instanceof String) {
            return objectMapper.writeValueAsString(ResponseData.success(o));
        }
        return ResponseData.success(o);
    }
}