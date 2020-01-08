package com.tim.iot.platform.register.domain.service;

import com.tim.iot.platform.register.web.register.protocol.exception.NotSupportDeviceTypeException;

public interface IQrCodeGenerate {
    String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException;
}
