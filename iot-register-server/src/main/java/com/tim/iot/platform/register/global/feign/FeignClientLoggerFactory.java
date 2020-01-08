package com.tim.iot.platform.register.global.feign;


import feign.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * @author tell.tim
 */
public class FeignClientLoggerFactory implements FeignLoggerFactory {

    @Override
    public Logger create(Class<?> type) {
        return new FeignClientLogger(LoggerFactory.getLogger(type));
    }
}