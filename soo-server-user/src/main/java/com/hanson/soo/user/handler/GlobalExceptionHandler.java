package com.hanson.soo.user.handler;

import com.hanson.soo.user.exception.TokenAuthorizationException;
import com.hanson.soo.common.response.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public MyExceptionHandler exception(Exception e) {
        if (e instanceof InvalidParameterException) {
            return MyExceptionHandler.fail(ResponseCode.RC400);
        } else if (e instanceof TokenAuthorizationException) {
            return MyExceptionHandler.fail(ResponseCode.RC401);
        }
        return MyExceptionHandler.fail(ResponseCode.RC500);
    }
}
