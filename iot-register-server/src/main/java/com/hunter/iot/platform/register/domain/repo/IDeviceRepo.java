package com.hunter.iot.platform.register.domain.repo;

import com.hunter.iot.platform.register.domain.model.entity.Device;

public interface IDeviceRepo {
    Device queryByDeviceId(String deviceId);

    void saveOrUpdate(String deviceId, String mac, String imei, Long timestamp);

}
