package com.tim.iot.platform.register.domain.service.impl;

import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.domain.repo.IDeviceRepo;
import com.tim.iot.platform.register.domain.service.IDeviceService;
import com.tim.iot.platform.register.domain.service.IQrCodeGenerate;
import com.tim.iot.platform.register.web.register.protocol.exception.NotSupportDeviceTypeException;

public class DeviceServiceImpl implements IDeviceService {


    private IDeviceRepo deviceRepo;
    private IQrCodeGenerate qrCodeGenerate;

    public DeviceServiceImpl(IDeviceRepo deviceRepo, IQrCodeGenerate qrCodeGenerate) {
        this.deviceRepo = deviceRepo;
        this.qrCodeGenerate = qrCodeGenerate;
    }


    @Override
    public Device findByDeviceId(String deviceId) {
        return deviceRepo.queryByDeviceId(deviceId);
    }

    @Override
    public void updateBindShip(Long deviceIndex, Boolean bindFlag, String account, Long timestamp) {
        deviceRepo.updateBindShip(deviceIndex,bindFlag,account,timestamp);
    }

    @Override
    public void update(Long deviceIndex,String mac,String imei, Long timestamp) {
        deviceRepo.update(deviceIndex, mac,imei,timestamp);
    }

    @Override
    public void save(String deviceId, String mac, String imei, Long timestamp) {
        deviceRepo.save(deviceId, mac, imei, timestamp);
    }


    @Override
    public String generateQrCode(String deviceId, String mac, String imei, Long timestamp, String type) throws NotSupportDeviceTypeException {
        return qrCodeGenerate.generateQrCode(deviceId, mac, imei, timestamp, type);
    }

    @Override
    public void correctProperty(Long deviceIndex, String mac, String imei) {
        deviceRepo.updateProperty(deviceIndex, mac,imei);
    }

    @Override
    public void updateTimestamp(Long deviceIndex, Long timestamp) {
        deviceRepo.updateTimestamp(deviceIndex, timestamp);
    }
}
