package com.hunter.iot.platform.auth.domain.repo.impl;

import com.hunter.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import com.hunter.iot.platform.auth.config.websocket.manager.SocketManager;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionCacheRepo implements IWebSocketSessionRepo {


    @Override
    public void clear(String deviceId) {
        SocketManager.removeAndClose(deviceId);
    }

    @Override
    public WebSocketSession get(String deviceId) {
        return SocketManager.get(deviceId);
    }

    @Override
    public void add(String deviceId, WebSocketSession session) {
        SocketManager.add(deviceId,session);
    }

    @Override
    public WebSocketSession remove(String deviceId) {
        return SocketManager.remove(deviceId);
    }


}
