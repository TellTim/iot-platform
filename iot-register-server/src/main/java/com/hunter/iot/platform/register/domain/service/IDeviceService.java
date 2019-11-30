package com.hunter.iot.platform.register.domain.service;

import com.hunter.iot.platform.register.domain.model.entity.Device;
import com.hunter.iot.platform.register.web.protocol.exception.NotSupportDeviceTypeException;

public interface IDeviceService {
    Device findByDeviceId(String deviceId);

    void saveOrUpdate(String deviceId, String mac, String imei, Long timestamp);

    String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException;
}
