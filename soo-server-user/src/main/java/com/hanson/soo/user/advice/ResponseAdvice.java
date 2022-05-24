package com.hanson.soo.user.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.soo.common.response.ResponseCode;
import com.hanson.soo.common.response.ResponseData;
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
        // 发生了异常
        if (o instanceof ResponseCode) {
            logger.error("错误码："+ ((ResponseCode) o).getCode() + "，错误信息："+ ((ResponseCode) o).getMessage());
            return ResponseData.fail((ResponseCode) o);
        }
        // 正常返回
        if(o instanceof String) {
            return objectMapper.writeValueAsString(ResponseData.success(o));
        }
        return ResponseData.success(o);
    }
}