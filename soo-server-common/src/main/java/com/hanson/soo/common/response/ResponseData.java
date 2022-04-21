package com.hanson.soo.common.response;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 统一数据返回
 * @param <T>
 */
@Data
public class ResponseData<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resultData = new ResponseData<>();
        BeanUtils.copyProperties(ResponseCode.RC200, resultData);
        resultData.setData(data);
        resultData.setSuccess(true);
        return resultData;
    }

    public static <T> ResponseData<T> fail(ResponseCode responseCode) {
        ResponseData<T> resultData = new ResponseData<>();
        BeanUtils.copyProperties(responseCode, resultData);
        resultData.setData(null);
        resultData.setSuccess(false);
        return resultData;
    }
}
