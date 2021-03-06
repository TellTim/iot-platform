package com.tim.iot.platform.auth.web;

import com.tim.iot.platform.auth.config.aop.api.ApiVersion;
import com.tim.iot.platform.auth.domain.service.IDeviceAuthMessageService;
import com.tim.iot.platform.auth.web.protocol.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "api")
public class AuthController {

    private final IDeviceAuthMessageService deviceMessageService;

    @Autowired
    public AuthController(IDeviceAuthMessageService deviceMessageService) {
        this.deviceMessageService = deviceMessageService;
    }

    /**
     * 可用于检查服务器是否瘫痪
     *
     * @return String
     */
    @GetMapping("/test")
    public String test() {
        log.info("alive test");
        return "alive";
    }

    @ApiVersion(1)
    @PostMapping("/{version}/confirm")
    @ResponseBody
    public Auth.Result success(@RequestBody Auth.Param param){
        try {
            deviceMessageService.sendAuthSuccess(param.getDeviceId(),param.getAccount(),param.getAuthAt());
        } catch (Exception e) {
            log.error("下发消息异常"+e.getMessage());
        }
        return Auth.Result.buildOk();
    }
}
