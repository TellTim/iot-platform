package com.iot.eureka.produce.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Tell.Tim
 * @date : 2020/1/7 14:50
 * @fileName : ProduceController
 */
@RestController
public class ProduceController {

    @GetMapping(value = "test")
    public String test() {
        return "produce active";
    }

}
