package com.websocket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "netty")
@Component
@Data
public class WebSocketProperties {
    private Integer boss;
    private Integer worker;
    private String host;
    private Integer port;
}
