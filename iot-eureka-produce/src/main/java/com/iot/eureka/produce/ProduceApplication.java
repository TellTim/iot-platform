package com.iot.eureka.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 14:54
 * @fileName : ProduceApplication
 */
@SpringBootApplication
@EnableEurekaClient
public class ProduceApplication {
    public static void main(String[] args){
        SpringApplication.run(ProduceApplication.class,args);
    }
}
