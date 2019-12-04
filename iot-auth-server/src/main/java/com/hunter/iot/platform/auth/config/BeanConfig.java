package com.hunter.iot.platform.auth.config;


import com.hunter.iot.platform.auth.config.websocket.handler.AuthWebSocketHandler;
import com.hunter.iot.platform.auth.domain.repo.IWebSocketSessionRepo;
import com.hunter.iot.platform.auth.domain.repo.impl.WebSocketSessionCacheRepo;
import com.hunter.iot.platform.auth.domain.service.IDeviceAuthMessageService;
import com.hunter.iot.platform.auth.domain.service.impl.DeviceAuthMessageService;
import com.hunter.iot.platform.auth.config.websocket.handshake.AuthHandshakeHandler;
import com.hunter.iot.platform.auth.config.websocket.handshake.AuthHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
public class BeanConfig {

    @Bean
    public IWebSocketSessionRepo webSocketSessionRepo(){
        return new WebSocketSessionCacheRepo();
    }

    @Bean
    public IDeviceAuthMessageService deviceAuthMessageService(){
        return new DeviceAuthMessageService();
    }

    @Bean
    public WebSocketHandler webSocketHandler(){
        return new AuthWebSocketHandler();
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor(){
        return new AuthHandshakeInterceptor();
    }

    @Bean
    public HandshakeHandler handshakeHandler(){
        return new AuthHandshakeHandler();
    }
}
