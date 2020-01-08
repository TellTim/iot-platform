package com.tim.iot.platform.register.global.feign;


import org.slf4j.Logger;

/**
 * @author tell.tim
 */
public class FeignClientLogger extends feign.Logger {

    private final Logger logger;

    public FeignClientLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }
}