package com.hunter.iot.platform.auth.config.websocket.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public class SocketManager {

    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL  = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("新添加webSocket连接 {} ", key);
        SESSION_POOL.put(key, webSocketSession);
    }

    public static WebSocketSession remove(String key) {
        log.info("移除webSocket连接 {} ", key);
        return SESSION_POOL.remove(key);
    }

    public static WebSocketSession get(String key) {
        WebSocketSession result = SESSION_POOL.get(key);
        if (result!=null) {
            log.info("获取session {},sessionId is {} ", key, result.getId());
        }
        return result;
    }

    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("关闭session异常"+e.getMessage());
            }
        }
    }
}
