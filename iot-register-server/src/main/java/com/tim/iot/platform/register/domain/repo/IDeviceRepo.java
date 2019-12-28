package com.tim.iot.platform.register.domain.repo;

import com.tim.iot.platform.register.domain.model.entity.Device;

public interface IDeviceRepo {
    Device queryByDeviceId(String deviceId);

    void update(Long deviceIndex, String mac,String imei,Long timestamp);

    void updateBindShip(Long deviceIndex, Boolean bindFlag, String account, Long timestamp);

    void save(String deviceId, String mac, String imei, Long timestamp);

    void updateProperty(Long deviceIndex, String mac, String imei);

    void updateTimestamp(Long deviceIndex, Long timestamp);
}
