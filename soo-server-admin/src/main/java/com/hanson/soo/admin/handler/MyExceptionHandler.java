package com.hanson.soo.admin.handler;

import com.hanson.soo.common.response.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
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