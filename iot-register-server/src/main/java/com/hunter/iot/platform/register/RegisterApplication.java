package com.hunter.iot.platform.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hunter.iot.platform.register")
@MapperScan("com.hunter.iot.platform.register.global.dao")
public class RegisterApplication {
    public static void main(String[] args){
        SpringApplication.run(RegisterApplication.class,args);
    }
}
