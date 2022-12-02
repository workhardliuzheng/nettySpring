package com.websocket;

import com.websocket.anno.EnableWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableWebSocketServer
public class WebSocket {
    public static void main(String[] args) {
        SpringApplication.run(WebSocket.class, args);
    }
}
