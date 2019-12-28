package com.tim.iot.platform.register.domain.repo.impl;

import com.tim.iot.platform.register.domain.model.entity.Device;
import com.tim.iot.platform.register.domain.repo.IDeviceRepo;
import com.tim.iot.platform.register.global.dao.DeviceRecord;
import com.tim.iot.platform.register.global.dao.DeviceRecordExample;
import com.tim.iot.platform.register.global.dao.DeviceRecordMapper;
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

    /**
     * 更新绑定关系
     * @param deviceIndex Long
     * @param bindFlag Boolean
     * @param account String
     * @param timestamp Long
     */
    @Override
    public synchronized void updateBindShip(Long deviceIndex, Boolean bindFlag, String account, Long timestamp) {
        DeviceRecord boxRecord = new DeviceRecord();
        boxRecord.setCreateAt(timestamp);
        boxRecord.setId(deviceIndex);
        boxRecord.setAccount(account);
        boxRecord.setBindFlag(bindFlag);
        try {
            deviceRecordMapper.updateByPrimaryKeySelective(boxRecord);
        }catch (Exception e){
            log.error("updateBindShip exception: "+e.getMessage());
        }
    }

    @Override
    public synchronized void update(Long deviceIndex,String mac,String imei, Long timestamp) {
        DeviceRecord boxRecord = new DeviceRecord();
        boxRecord.setCreateAt(timestamp);
        boxRecord.setId(deviceIndex);
        boxRecord.setMacAddress(mac);
        boxRecord.setImei(imei);
        try {
            deviceRecordMapper.updateByPrimaryKeySelective(boxRecord);
        }catch (Exception e){
            log.error("update exception: "+e.getMessage());
        }
    }

    @Override
    public synchronized void save(String deviceId, String mac, String imei, Long timestamp) {
        DeviceRecord boxRecord = new DeviceRecord();
        boxRecord.setDeviceId(deviceId);
        boxRecord.setCreateAt(timestamp);
        boxRecord.setMacAddress(mac);
        boxRecord.setImei(imei);
        boxRecord.setBindFlag(false);
        boxRecord.setAccount("");
        try {
            deviceRecordMapper.insert(boxRecord);
        }catch (Exception e){
            log.error("save exception: "+e.getMessage());
        }
    }

    @Deprecated
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
