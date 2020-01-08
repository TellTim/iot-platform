package com.iot.eureka.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 15:33
 * @fileName : ConsumerService
 */
@Service
public class ConsumerService {
    @Autowired
    private RestTemplate restTemplate;

    public String testConsumer(){
        return restTemplate.getForObject("http://iot-eureka-produce/test",String.class);
    }
}
