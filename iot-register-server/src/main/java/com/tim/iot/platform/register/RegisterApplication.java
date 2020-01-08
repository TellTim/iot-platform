package com.tim.iot.platform.register;

import com.tim.iot.platform.register.global.feign.FeignClientConfig;
import com.tim.iot.platform.register.web.bind.AuthClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tell.tim
 */
@SpringBootApplication(scanBasePackages = "com.tim.iot.platform.register")
@MapperScan("com.tim.iot.platform.register.global.dao")
@EnableAsync
@EnableFeignClients(
        clients = {AuthClient.class},
        defaultConfiguration = FeignClientConfig.class
)
public class RegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }
}
