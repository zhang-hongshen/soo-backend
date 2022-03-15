package com.hanson.soo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.soo.common.dao")
public class SooServerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooServerUserApplication.class, args);
    }

}
