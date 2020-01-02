package com.tim.iot.platform.auth.config.websocket.handshake;

import com.tim.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private IWebSocketSessionRepo webSocketSessionRepo;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attribute) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletServerHttpRequest.getServletRequest();
            final String deviceId = httpRequest.getParameter("deviceId");
            if (StringUtils.isNotEmpty(deviceId)) {
                log.info(" --> 即将握手，设备id是 is {}");
                return true;
            }
        }
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse
            serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info(" --> 握手结束");
    }
}
