package com.hanson.soo.admin.handler;

import com.hanson.soo.common.api.ResponseCode;
import com.hanson.soo.common.api.ResponseData;
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
