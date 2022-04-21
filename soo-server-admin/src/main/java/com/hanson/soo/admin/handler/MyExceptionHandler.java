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
    ResponseCode responseCode;

    public static MyExceptionHandler fail(ResponseCode responseCode) {
        MyExceptionHandler exceptionHandler = new MyExceptionHandler();
        BeanUtils.copyProperties(responseCode, exceptionHandler);
        return exceptionHandler;
    }
}