package com.hunter.iot.platform.register.web;


import com.hunter.iot.platform.register.domain.model.entity.Device;
import com.hunter.iot.platform.register.domain.model.vojvalue.ResponseCode;
import com.hunter.iot.platform.register.domain.service.IDeviceService;
import com.hunter.iot.platform.register.web.protocol.Check;
import com.hunter.iot.platform.register.web.protocol.Register;
import com.hunter.iot.platform.register.web.protocol.exception.NotSupportDeviceTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @GetMapping("/test")
    public String test() {
        return "test register";
    }

    @PostMapping("/check")
    @ResponseBody
    public Check.Result check(@RequestBody Check.Param param) {
        Check.Result result = new Check.Result();
        Device device = deviceService.findByDeviceId(param.getDeviceId());
        if (device == null) {
            result.setCode(ResponseCode.DEVICE_NOT_EXIST.getCode());
            result.setMsg(ResponseCode.DEVICE_NOT_EXIST.getData());
            return result;
        } else {
            if (device.getBindFlag()) {
                result.setCode(ResponseCode.SUCCESS.getCode());
                result.setAccount(device.getAccount());
            } else {
                result.setCode(ResponseCode.BIND_NOT_EXIST.getCode());
            }
            return result;
        }
    }


    @PostMapping("/register")
    @ResponseBody
    public Register.Result register(@RequestBody Register.Param param) throws NotSupportDeviceTypeException {
        new Thread(() -> deviceService.saveOrUpdate(param.getDeviceId(), param.getMac(), param.getImei(), param.getTimestamp())).start();
        Register.Result result = new Register.Result();
        String qrCode = deviceService.generateQrCode(param.getDeviceId(), param.getMac(), param.getImei(), param.getTimestamp(), param.getType());
        result.setCode(ResponseCode.SUCCESS.getCode());
        result.setQrCode(qrCode);
        result.setExpireIn(120);
        return result;
    }
}
