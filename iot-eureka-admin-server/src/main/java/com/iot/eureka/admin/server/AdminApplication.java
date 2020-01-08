package com.iot.eureka.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 21:04
 * @fileName : AdminApplication
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class,args);
    }
}
