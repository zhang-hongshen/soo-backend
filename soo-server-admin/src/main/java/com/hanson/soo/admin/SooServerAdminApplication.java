package com.hanson.soo.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.soo.common.dao")
public class SooServerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooServerAdminApplication.class, args);
    }

}
