package com.hunter.iot.platform.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hunter.iot.platform.auth")
public class AuthApplication {
    public static void main(String[] args){
        SpringApplication.run(AuthApplication.class,args);
    }
}
