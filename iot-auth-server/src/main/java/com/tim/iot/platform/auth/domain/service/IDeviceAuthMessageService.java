package com.tim.iot.platform.auth.domain.service;

import java.io.IOException;

public interface IDeviceAuthMessageService {
    void sendAuthSuccess(String deviceId, String account,Long authAt) throws IOException;
}
