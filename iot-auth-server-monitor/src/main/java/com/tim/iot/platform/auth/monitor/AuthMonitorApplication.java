package com.tim.iot.platform.auth.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 20:47
 * @fileName : AuthMonitorApplication
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class AuthMonitorApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthMonitorApplication.class,args);
    }

}
