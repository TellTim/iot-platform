package com.tim.iot.platform.auth.domain.service.impl;

import com.tim.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import com.tim.iot.platform.auth.domain.service.IDeviceAuthMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@Slf4j
public class DeviceAuthMessageService implements IDeviceAuthMessageService {

    @Autowired
    private IWebSocketSessionRepo webSocketSessionRepo;

    @Override
    public void sendAuthSuccess(String deviceId, String account,Long authAt) throws IOException {
        String message = String.format("confirm#%s#%s",account,String.valueOf(authAt));
        log.debug("ddd "+message);
        webSocketSessionRepo.get(deviceId).sendMessage(new TextMessage(message));
    }
}
