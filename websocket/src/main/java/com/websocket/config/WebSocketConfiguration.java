package com.websocket.config;

import com.websocket.handler.WebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class WebSocketConfiguration {
    @Autowired
    private WebSocketProperties webSocketProperties;

    @Bean("workerNioEventLoopGroup")
    public NioEventLoopGroup workerNioEventLoopGroup() {
        return new NioEventLoopGroup(webSocketProperties.getWorker());
    }

    @Bean("bootNioEventLoopGroup")
    public NioEventLoopGroup bootNioEventLoopGroup() {
        return new NioEventLoopGroup(webSocketProperties.getBoss());
    }

    @Bean("serverBootStrap")
    public ServerBootstrap serverBootStrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bootNioEventLoopGroup(), workerNioEventLoopGroup())
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketChannelInitializer());
        return serverBootstrap;
    }
}
