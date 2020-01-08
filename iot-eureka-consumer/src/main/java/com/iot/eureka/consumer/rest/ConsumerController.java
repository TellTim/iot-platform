package com.iot.eureka.consumer.rest;

import com.iot.eureka.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 15:37
 * @fileName : ConsumerController
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @GetMapping(value = "test")
    public String test() {
        return service.testConsumer();
    }


}
