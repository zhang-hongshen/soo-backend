package com.hanson.soo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.hanson.soo.user", "com.hanson.soo.common"})
public class SooServerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooServerUserApplication.class, args);
    }

}
