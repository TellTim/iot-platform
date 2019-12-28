package com.tim.iot.platform.auth.config.websocket.handshake;

import com.tim.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Slf4j
public class AuthHandshakeHandler extends DefaultHandshakeHandler {

    @Autowired
    private IWebSocketSessionRepo webSocketSessionRepo;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletServerHttpRequest.getServletRequest();
            final String deviceId = httpRequest.getParameter("deviceId");
            if (StringUtils.isEmpty(deviceId)) {
                return null;
            }
            if (webSocketSessionRepo.get(deviceId) != null) {
                log.warn(" --> 会话未断开,收到重复请求,请求的设备id是{}", deviceId);
                //webSocketSessionRepo.clear(deviceId);
            }
            return () -> deviceId;
        }
        return null;
    }
}
