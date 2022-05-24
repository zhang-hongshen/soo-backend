package com.hanson.soo.admin.handler;

import com.hanson.soo.admin.exception.TokenAuthorizationException;
import com.hanson.soo.common.response.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseCode invalidParameterException(IllegalArgumentException e) {
        logger.error(e.getMessage());
        return ResponseCode.RC400;
    }

    @ExceptionHandler(TokenAuthorizationException.class)
    public ResponseCode tokenAuthorizationException(TokenAuthorizationException e) {
        logger.error(e.getMessage());
        return ResponseCode.RC401;
    }

    @ExceptionHandler(Exception.class)
    public ResponseCode exception(Exception e) {
        logger.error(e.getMessage());
        return ResponseCode.RC500;
    }

}
