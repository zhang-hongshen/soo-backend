package com.hanson.soo.common.api;

/**
 * 状态码
 */
public enum ResponseCode {
    /**操作成功**/
    RC200(200,"操作成功"),
    /**服务异常**/
    RC500(500,"系统异常，请稍后重试");

    /**自定义状态码**/
    private final int code;
    /**自定义描述**/
    private final String message;

    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
