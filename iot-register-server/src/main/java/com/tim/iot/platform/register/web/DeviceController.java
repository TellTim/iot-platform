package com.tim.iot.platform.register.web;


import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.domain.model.vojvalue.Respond;
import com.tim.iot.platform.register.domain.service.IDeviceService;
import com.tim.iot.platform.register.global.aop.api.ApiVersion;
import com.tim.iot.platform.register.global.aop.time.TimeConsume;
import com.tim.iot.platform.register.web.protocol.Check;
import com.tim.iot.platform.register.web.protocol.Register;
import com.tim.iot.platform.register.web.protocol.account.AccountInfo;
import com.tim.iot.platform.register.web.protocol.exception.NotSupportDeviceTypeException;
import com.tim.iot.platform.register.web.protocol.qrcode.QrCodeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@Slf4j
@RequestMapping(value = "api")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;
    @Value("${auth.timeout}")
    private int authExpireIn;

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
    @PostMapping("/{version}/check")
    @ResponseBody
    public Check.Result check(@RequestBody Check.Param param) {
        Check.Result result = new Check.Result();
        Device device = deviceService.findByDeviceId(param.getDeviceId());
        if (device == null) {
            result.setCode(Respond.DEVICE_NOT_EXIST.getCode());
            result.setData(Respond.DEVICE_NOT_EXIST.getData());
            return result;
        } else {
            if (device.getBindFlag()) {
                result.setCode(Respond.SUCCESS.getCode());
                result.setAccount(device.getAccount());
            } else {
                result.setCode(Respond.BIND_NOT_EXIST.getCode());
            }
            return result;
        }
    }

    @ApiVersion(1)
    @PostMapping("/{version}/register")
    @ResponseBody
    @TimeConsume
    public Callable<Register.Result> register(@RequestBody Register.Param param) {
        return () -> {
            Register.Result result = new Register.Result();
            Device device = deviceService.findByDeviceId(param.getDeviceId());
            //首次注册
            if (device == null) {
                deviceService.save(param.getDeviceId(),param.getMac(),param.getImei(),param.getTimestamp());
                return getQrCodeInfoResult(param.getDeviceId(),param.getMac(),param.getImei(),param.getTimestamp(),param.getType());
            } else {
                //已注册,并且已绑定
                if (device.getBindFlag()){
                    result.setCode(Respond.BIND_EXIST.getCode());
                    result.setData(Respond.BIND_EXIST.getData());
                    AccountInfo accountInfo = new AccountInfo();
                    accountInfo.setAccount(device.getAccount());
                    accountInfo.setCreateAt(device.getCreateAt());
                    result.setAccountInfo(accountInfo);
                    return result;
                }else {
                    //已注册,未绑定
                    deviceService.update(device.getId(),param.getMac(),param.getImei(),param.getTimestamp());
                    return getQrCodeInfoResult(param.getDeviceId(),param.getMac(),param.getImei(),param.getTimestamp(),param.getType());
                }
            }
        };
    }

    private Register.Result getQrCodeInfoResult(String deviceId, String mac, String imei, Long timestamp,String type) {
        Register.Result qrCodeInfoResult = new Register.Result();
        try {
            qrCodeInfoResult.setCode(Respond.BIND_NOT_EXIST.getCode());
            qrCodeInfoResult.setData(Respond.BIND_NOT_EXIST.getData());
            QrCodeInfo qrCodeInfo = new QrCodeInfo();
            String qrCode = deviceService.generateQrCode(deviceId,mac,imei,timestamp,type);
            qrCodeInfo.setQrCode(qrCode);
            qrCodeInfo.setExpireIn(authExpireIn);
            qrCodeInfoResult.setQrCodeInfo(qrCodeInfo);
        }catch (NotSupportDeviceTypeException e){
            qrCodeInfoResult.setCode(Respond.TYPE_INVALID.getCode());
            qrCodeInfoResult.setData(Respond.TYPE_INVALID.getData());
        }
        return qrCodeInfoResult;
    }
}
