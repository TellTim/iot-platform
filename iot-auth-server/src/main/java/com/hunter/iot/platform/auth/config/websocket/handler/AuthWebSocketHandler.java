package com.hunter.iot.platform.auth.config.websocket.handler;

import com.hunter.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.security.Principal;

@Slf4j
public class AuthWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private IWebSocketSessionRepo webSocketSessionRepo;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("有人连接啦  sessionId = {}", session.getId());
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info("{} 连线!", principal.getName());
            webSocketSessionRepo.add(principal.getName(), session);
        }
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info("收到{}的消息:{}", principal.getName(),message.getPayload());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info("{} 连接异常 {}!", principal.getName(),exception.getMessage());
        }
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            webSocketSessionRepo.remove(principal.getName());
            log.info("{} 断开!", principal.getName());
        }
        super.afterConnectionClosed(session, status);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
