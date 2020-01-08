package com.tim.iot.platform.register.web.register;


import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.web.register.protocol.QrCode;
import com.tim.iot.platform.register.web.register.protocol.Register;
import com.tim.iot.platform.register.web.register.protocol.base.Respond;
import com.tim.iot.platform.register.domain.service.IDeviceService;
import com.tim.iot.platform.register.global.aop.api.ApiVersion;
import com.tim.iot.platform.register.global.aop.time.TimeConsume;
import com.tim.iot.platform.register.web.register.protocol.account.AccountInfo;
import com.tim.iot.platform.register.web.register.protocol.exception.NotSupportDeviceTypeException;
import com.tim.iot.platform.register.web.register.protocol.qrcode.QrCodeInfo;
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
    @PostMapping("/{version}/register")
    @ResponseBody
    @TimeConsume
    public Callable<Register.Result> register(@RequestBody Register.Param param) {
        return () -> {
            Register.Result result = new Register.Result();
            Device device = deviceService.findByDeviceId(param.getDeviceId());
            //首次注册
            if (device == null) {
                deviceService.save(param.getDeviceId(), param.getMac(), param.getImei(), param.getTimestamp());
                return Register.Result.buildNotBind();
            } else {
                //已注册,并且已绑定
                if (device.getBindFlag()) {
                    //矫正数据,由于部分设备的mac地址和imei可能有变更
                    if (!param.getImei().equals(device.getImei()) || param.getMac().equals(device.getMacAddress())) {
                        deviceService.correctProperty(device.getId(), param.getMac(), param.getImei());
                    }
                    result.setCode(Respond.BIND_EXIST.getCode());
                    result.setData(Respond.BIND_EXIST.getData());
                    AccountInfo accountInfo = new AccountInfo();
                    accountInfo.setAccount(device.getAccount());
                    accountInfo.setCreateAt(device.getCreateAt());
                    result.setAccountInfo(accountInfo);
                    return result;
                } else {
                    //已注册,未绑定
                    deviceService.update(device.getId(), param.getMac(), param.getImei(), param.getTimestamp());
                    return Register.Result.buildNotBind();
                }
            }
        };
    }


    @ApiVersion(1)
    @PostMapping("/{version}/qrcode")
    @ResponseBody
    @TimeConsume
    public Callable<QrCode.Result> qrCode(@RequestBody QrCode.Param param) {
        return () -> {
            QrCode.Result result = new QrCode.Result();
            Device device = deviceService.findByDeviceId(param.getDeviceId());
            if (device != null) {
                //已绑定
                if (device.getBindFlag()) {
                    result.setCode(Respond.BIND_EXIST.getCode());
                    result.setData(Respond.BIND_EXIST.getData());
                    AccountInfo accountInfo = new AccountInfo();
                    accountInfo.setAccount(device.getAccount());
                    accountInfo.setCreateAt(device.getCreateAt());
                    result.setAccountInfo(accountInfo);
                    return result;
                } else {
                    //未绑定
                    deviceService.updateTimestamp(device.getId(), param.getTimestamp());
                    return getQrCodeInfoResult(param.getDeviceId(), device.getMacAddress(), device.getImei(), param.getTimestamp(), param.getType());
                }
            } else {
                result.setCode(Respond.DEVICE_NOT_EXIST.getCode());
                result.setCode(Respond.DEVICE_NOT_EXIST.getData());
                log.error("device miss {}", param.getDeviceId());
                return QrCode.Result.noDeviceBuild();
            }
        };
    }

    private QrCode.Result getQrCodeInfoResult(String deviceId, String mac, String imei, Long timestamp, String type) {
        QrCode.Result qrCodeInfoResult = new QrCode.Result();
        try {
            qrCodeInfoResult.setCode(Respond.BIND_NOT_EXIST.getCode());
            qrCodeInfoResult.setData(Respond.BIND_NOT_EXIST.getData());
            QrCodeInfo qrCodeInfo = new QrCodeInfo();
            String qrCode = deviceService.generateQrCode(deviceId, mac, imei, timestamp, type);
            qrCodeInfo.setQrCode(qrCode);
            qrCodeInfo.setExpireIn(authExpireIn);
            qrCodeInfoResult.setQrCodeInfo(qrCodeInfo);
        } catch (NotSupportDeviceTypeException e) {
            qrCodeInfoResult.setCode(Respond.TYPE_INVALID.getCode());
            qrCodeInfoResult.setData(Respond.TYPE_INVALID.getData());
        }
        return qrCodeInfoResult;
    }

}
