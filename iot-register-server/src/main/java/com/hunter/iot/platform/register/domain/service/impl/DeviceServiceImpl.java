package com.hunter.iot.platform.register.domain.service.impl;

import com.hunter.iot.platform.register.domain.model.entity.Device;
import com.hunter.iot.platform.register.domain.repo.IDeviceRepo;
import com.hunter.iot.platform.register.domain.service.IDeviceService;
import com.hunter.iot.platform.register.domain.service.IQrCodeGenerate;
import com.hunter.iot.platform.register.web.protocol.exception.NotSupportDeviceTypeException;

public class DeviceServiceImpl implements IDeviceService {


    private IDeviceRepo deviceRepo;
    private IQrCodeGenerate qrCodeGenerate;

    public DeviceServiceImpl(IDeviceRepo deviceRepo, IQrCodeGenerate qrCodeGenerate) {
        this.deviceRepo = deviceRepo;
        this.qrCodeGenerate = qrCodeGenerate;
    }


    @Override
    public Device findByDeviceId(String deviceId) {
        Device device = deviceRepo.queryByDeviceId(deviceId);
        return device;
    }

    @Override
    public void saveOrUpdate(String deviceId, String mac, String imei, Long timestamp) {
        deviceRepo.saveOrUpdate(deviceId, mac, imei, timestamp);
    }

    @Override
    public String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException {
        return qrCodeGenerate.generateQrCode(deviceId, mac, imei, timestamp, type);
    }
}
