package com.hanson.soo.admin.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKeyPrefix {
    ADMIN_TOKEN("soo:admin:token:"),
    PRODUCT_INFO("soo:product:");

    private final String prefix;
}
