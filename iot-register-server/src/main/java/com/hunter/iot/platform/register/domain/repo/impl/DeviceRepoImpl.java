package com.hunter.iot.platform.register.domain.repo.impl;

import com.hunter.iot.platform.register.domain.model.entity.Device;
import com.hunter.iot.platform.register.global.dao.DeviceRecord;
import com.hunter.iot.platform.register.global.dao.DeviceRecordExample;
import com.hunter.iot.platform.register.global.dao.DeviceRecordMapper;
import com.hunter.iot.platform.register.domain.repo.IDeviceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class DeviceRepoImpl implements IDeviceRepo {

    @Autowired
    private DeviceRecordMapper deviceRecordMapper;


    @Override
    public Device queryByDeviceId(String deviceId) {
        DeviceRecordExample example = new DeviceRecordExample();
        example.createCriteria().andDeviceIdEqualTo(deviceId);
        for (DeviceRecord item : deviceRecordMapper.selectByExample(example)) {
            return new Device(item.getId(), item.getDeviceId(), item.getMacAddress(), item.getImei(),
                    item.getCreateAt(), item.getBindFlag(), item.getAccount());
        }
        return null;
    }

    @Override
    public void saveOrUpdate(String deviceId, String mac, String imei, Long timestamp) {
        Device device = queryByDeviceId(deviceId);
        DeviceRecord boxRecord = new DeviceRecord();
        boxRecord.setDeviceId(deviceId);
        boxRecord.setCreateAt(timestamp);
        boxRecord.setMacAddress(mac);
        boxRecord.setImei(imei);
        try {
            if (device != null) {
                boxRecord.setBindFlag(device.getBindFlag());
                boxRecord.setId(device.getId());
                boxRecord.setAccount(device.getAccount());
                deviceRecordMapper.updateByPrimaryKeySelective(boxRecord);
            } else {
                boxRecord.setBindFlag(false);
                boxRecord.setAccount("");
                deviceRecordMapper.insert(boxRecord);
            }
        } catch (Exception e) {
            log.error("saveDevice " + e.getMessage());
        }
    }

}
