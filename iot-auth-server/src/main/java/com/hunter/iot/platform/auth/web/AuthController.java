package com.hunter.iot.platform.auth.web;

import com.hunter.iot.platform.auth.domain.service.IDeviceAuthMessageService;
import com.hunter.iot.platform.auth.web.protocol.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(value = "confirm")
public class AuthController {

    private final IDeviceAuthMessageService deviceMessageService;

    @Autowired
    public AuthController(IDeviceAuthMessageService deviceMessageService) {
        this.deviceMessageService = deviceMessageService;
    }

    @PostMapping("/success")
    @ResponseBody
    public Auth.Result success(@RequestBody Auth.Param param){
        try {
            deviceMessageService.sendAuthSuccess(param.getDeviceId(),param.getAccount());
        } catch (Exception e) {
            log.error("下发成功消息异常"+e.getMessage());
        }
        return Auth.Result.buildOk();
    }


}
