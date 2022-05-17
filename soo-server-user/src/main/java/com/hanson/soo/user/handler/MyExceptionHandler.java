package com.hanson.soo.user.handler;

import com.hanson.soo.common.response.ResponseCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MyExceptionHandler {
    private ResponseCode responseCode;

    public static MyExceptionHandler fail(ResponseCode responseCode) {
        MyExceptionHandler exceptionHandler = new MyExceptionHandler();
        exceptionHandler.setResponseCode(responseCode);
        return exceptionHandler;
    }
}