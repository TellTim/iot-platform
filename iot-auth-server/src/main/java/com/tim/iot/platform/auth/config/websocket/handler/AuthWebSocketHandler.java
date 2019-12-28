package com.tim.iot.platform.auth.config.websocket.handler;

import com.tim.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
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
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info(" --> {}连接建立,插入缓存,会话id是{}", principal.getName(), session.getId());
            webSocketSessionRepo.add(principal.getName(), session);
            /**
             * 需要服务端先给客户端发送PING消息，启动与客户端的PING/PONG应答
             * 由于接受PONG消息很频繁,暂时关闭.
             */
            //handlePingMessage(session);
        } else {
            log.warn(" --> 连接建立，会话id是{}", session.getId());
        }

    }

    private void handlePingMessage(WebSocketSession session) {
        byte[] bs = new byte[1];
        bs[0] = 'i';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PingMessage pingMessage = new PingMessage(byteBuffer);
        try {
            session.sendMessage(pingMessage);
        } catch (IOException e) {
            log.error(" --> 发送pong消息异常" + e.getMessage());
        }
        log.info(" --> 发送ping消息");
    }

    private void handlePongMessage(WebSocketSession session) {
        byte[] bs = new byte[1];
        bs[0] = 'o';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PongMessage pongMessage = new PongMessage(byteBuffer);
        try {
            session.sendMessage(pongMessage);
        } catch (IOException e) {
            log.error(" --> 发送pong消息异常" + e.getMessage());
        }
        log.info(" --> 发送pong消息");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof PongMessage) {
            handlePongMessage(session, (PongMessage) message);
        } else if (message instanceof TextMessage) {
            handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof PingMessage) {
            handlePingMessage(session, (PingMessage) message);
        } else {
            log.error(" --> 收到错误格式的消息");
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("messages not supported"));
        }
    }

    private void handleTextMessage(WebSocketSession session, TextMessage message) {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info(" --> 收到{}的消息:{}", principal.getName(), message.getPayload());
        }
        log.info(" --> 收到的消息:{}", message.getPayload());
    }

    private void handlePingMessage(WebSocketSession session, PingMessage message) {
        log.info(" --> 收到的Ping消息:{}", message.getPayload());
        byte[] bs = new byte[1];
        bs[0] = 'o';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PingMessage pingMessage = new PingMessage(byteBuffer);
        try {
            session.sendMessage(pingMessage);
        } catch (IOException e) {
            log.error(" --> 发送pong消息异常" + e.getMessage());
        }
    }


    private void handlePongMessage(WebSocketSession session, PongMessage message) {
        log.info(" --> 收到的Pong消息:{},来自{}", message.getPayload(), session.getRemoteAddress().getAddress().getHostAddress());
        byte[] bs = new byte[1];
        bs[0] = 'i';
        ByteBuffer byteBuffer = ByteBuffer.wrap(bs);
        PingMessage pingMessage = new PingMessage(byteBuffer);
        try {
            session.sendMessage(pingMessage);
        } catch (IOException e) {
            log.error(" --> 发送ping消息异常" + e.getMessage());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            log.info(" --> {} 连接,会话id是{},异常信息:{}!", principal.getName(), session.getId(), exception.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Principal principal = session.getPrincipal();
        if (principal != null) {
            webSocketSessionRepo.remove(principal.getName());
            log.info(" --> {}的会话id是{}的连接已断开", principal.getName(), session.getId());
        } else {
            log.warn(" --> 会话id是{}的连接已断开", session.getId());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
