package com.tim.iot.platform.register.web.bind;

import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.domain.service.IDeviceService;
import com.tim.iot.platform.register.global.aop.api.ApiVersion;
import com.tim.iot.platform.register.web.bind.protocol.Auth;
import com.tim.iot.platform.register.web.bind.protocol.Bind;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

/**
 * @author : Tell.Tim
 * @date : 2020/1/6 20:27
 * @fileName : BindController
 */
@RestController
@Slf4j
@RequestMapping(value = "bind/api")
public class BindController {

    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private AuthClient authClient;

    @ApiVersion(1)
    @PostMapping(value = "{version}/confirm")
    @ResponseBody
    public Callable<Bind.Result> success(@RequestBody Bind.Param param) {
        return () -> {
            Device device = deviceService.findByDeviceId(param.getDeviceId());
            if (device == null) {
                return Bind.Result.DEVICE_NOT_EXIST_RESULT;
            } else {
                deviceService.updateBindShip(device.getId(), true, param.getAccount(), param.getAuthAt());
                Auth.Param authParam = new Auth.Param();
                authParam.setAccount(param.getAccount());
                authParam.setDeviceId(param.getDeviceId());
                authParam.setAuthAt(param.getAuthAt());
                authClient.confirm(authParam);
                Bind.Result result = new Bind.Result();
                result.setCode("200");
                result.setData("success");
                return result;
            }
        };
    }
}
