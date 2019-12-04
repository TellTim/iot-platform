package com.hunter.iot.platform.auth.config.websocket.handler;

import com.hunter.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Principal;

@Slf4j
public class AuthWebSocketHandler implements WebSocketHandler {

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
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("收到的消息");
        if (message instanceof PongMessage){
            handlePongMessage(session, (PongMessage) message);
        }else if (message instanceof TextMessage) {
            handleTextMessage(session, (TextMessage) message);
        }else if (message instanceof PingMessage){
            handlePingMessage(session, (PingMessage) message);
        }else {
            log.error("收到错误格式的消息");
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("messages not supported"));
        }
    }

    private void handleTextMessage(WebSocketSession session, TextMessage message) {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info("收到{}的消息:{}", principal.getName(),message.getPayload());
        }

        log.info("收到的消息:{}",message.getPayload());
    }

    private void handlePingMessage(WebSocketSession session, PingMessage message) {
        byte[] bs = new byte[1];
        bs[0] = 'o';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PingMessage pingMessage = new PingMessage(byteBuffer);
        try {
            session.sendMessage(pingMessage);
        } catch (IOException e) {
            log.error("发送pong消息异常"+e.getMessage());
        }
    }


    private void handlePongMessage(WebSocketSession session, PongMessage message) {
        byte[] bs = new byte[1];
        bs[0] = 'i';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PingMessage pingMessage = new PingMessage(byteBuffer);
        try {
            session.sendMessage(pingMessage);
        } catch (IOException e) {
            log.error("发送ping消息异常"+e.getMessage());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info("{} 连接异常 {}!", principal.getName(),exception.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            webSocketSessionRepo.remove(principal.getName());
            log.info("{} 断开!", principal.getName());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
