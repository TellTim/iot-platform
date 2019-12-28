package com.tim.iot.platform.auth.domain.repo;

import org.springframework.web.socket.WebSocketSession;

public interface IWebSocketSessionRepo {
    void clear(String deviceId);

    WebSocketSession get(String deviceId);

    void add(String deviceId, WebSocketSession session);

    WebSocketSession remove(String deviceId);
}
