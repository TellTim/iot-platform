package com.tim.iot.platform.register.domain.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Device {

    private Long id;

    private String deviceId;

    private String macAddress;

    private String imei;

    private Long createAt;

    private Boolean bindFlag;

    private String account;

    public Device(Long id, String deviceId, String macAddress, String imei, Long createAt, Boolean bindFlag,String account) {
        this.id = id;
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.imei = imei;
        this.createAt = createAt;
        this.bindFlag = bindFlag;
        this.account = account;
    }
}
