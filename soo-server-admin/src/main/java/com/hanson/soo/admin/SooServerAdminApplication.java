package com.hanson.soo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.hanson.soo.admin","com.hanson.soo.common"})
public class SooServerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooServerAdminApplication.class, args);
    }

}
