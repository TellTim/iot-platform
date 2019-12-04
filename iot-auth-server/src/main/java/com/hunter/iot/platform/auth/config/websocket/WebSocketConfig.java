package com.hunter.iot.platform.auth.config.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


/**
 * EnableWebSocketMessageBroker 表示使用STOMP协议来传输基于消息代理的消息，
 * 此时可以在@Controller类中使用@MessageMapping
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final WebSocketHandler authWebSocketHandler;
    private final HandshakeInterceptor authHandshakeInterceptor;
    private final HandshakeHandler authHandshakeHandler;

    @Autowired
    public WebSocketConfig(WebSocketHandler authWebSocketHandler, HandshakeInterceptor authHandshakeInterceptor,HandshakeHandler authHandshakeHandler) {
        this.authWebSocketHandler = authWebSocketHandler;
        this.authHandshakeInterceptor = authHandshakeInterceptor;
        this.authHandshakeHandler = authHandshakeHandler;

    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(authWebSocketHandler, "/check")
                .addInterceptors(authHandshakeInterceptor)
                .setHandshakeHandler(authHandshakeHandler)
                .setAllowedOrigins("*");
    }
}
