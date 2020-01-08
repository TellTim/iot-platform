package com.tim.iot.platform.register.global.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tell.tim
 */
@Configuration
public class FeignClientConfig {

    @Bean
    Request.Options feignRequestOption(){
        return new Request.Options(5000,30000);
    }

    @Bean
    Retryer feignRetry(){
        return  Retryer.NEVER_RETRY;
    }

    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    FeignLoggerFactory feignClientLoggerFactory() {
        return new FeignClientLoggerFactory();
    }
}
