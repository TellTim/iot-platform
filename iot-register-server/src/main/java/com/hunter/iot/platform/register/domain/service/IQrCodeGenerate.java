package com.hunter.iot.platform.register.domain.service;

import com.hunter.iot.platform.register.web.protocol.exception.NotSupportDeviceTypeException;

public interface IQrCodeGenerate {
    String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException;
}
