package com.hanson.soo.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.soo.common.dao")
public class SooServerCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooServerCommonApplication.class, args);
    }

}
