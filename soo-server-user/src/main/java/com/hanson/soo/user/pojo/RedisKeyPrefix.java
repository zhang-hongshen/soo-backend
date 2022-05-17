package com.hanson.soo.user.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKeyPrefix {
    USER_TOKEN("soo:user:token:"),
    PRODUCT_INFO("soo:product:");

    private final String prefix;
}
