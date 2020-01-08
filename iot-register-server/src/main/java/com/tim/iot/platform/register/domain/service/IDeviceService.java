package com.tim.iot.platform.register.domain.service;

import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.web.register.protocol.exception.NotSupportDeviceTypeException;

public interface IDeviceService {
    Device findByDeviceId(String deviceId);

    void update(Long deviceIndex,String mac,String imei, Long timestamp);

    void updateBindShip(Long deviceIndex, Boolean bindFlag, String account, Long timestamp);

    void save(String deviceId, String mac, String imei, Long timestamp);

    String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException;

    void correctProperty(Long deviceIndex, String mac, String imei);

    void updateTimestamp(Long deviceIndex, Long timestamp);
}
