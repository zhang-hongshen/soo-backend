package com.hanson.soo.user.handler;

import com.hanson.soo.common.response.ResponseCode;
import com.hanson.soo.common.response.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<String> exception(Exception e) {
        return ResponseData.fail(ResponseCode.RC500);
    }
}
