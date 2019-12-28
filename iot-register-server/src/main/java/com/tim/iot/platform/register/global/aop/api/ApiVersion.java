package com.tim.iot.platform.register.global.aop.api;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author : Tell.Tim
 * @date : 2019/12/24 14:51
 * @fileName : ApiVersion
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    int value();
}
