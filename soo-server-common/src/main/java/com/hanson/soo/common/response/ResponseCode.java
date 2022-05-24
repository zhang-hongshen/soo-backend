package com.hanson.soo.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    // 操作成功
    RC200(200,"操作成功"),
    // 客户端异常
    RC400(400, "参数异常"),
    RC401(401, "权限验证失败"),
    // 服务端异常
    RC500(500,"系统异常，请稍后重试");

    // 状态码
    private final int code;
    // 描述信息
    private final String message;
}
