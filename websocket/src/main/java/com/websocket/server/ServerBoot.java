package com.websocket.server;

import com.websocket.config.WebSocketProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class ServerBoot {
    @Autowired
    private WebSocketProperties webSocketProperties;
    @Autowired
    @Qualifier("workerNioEventLoopGroup")
    private NioEventLoopGroup workerNioEventLoopGroup;
    @Autowired
    @Qualifier("bootNioEventLoopGroup")
    private NioEventLoopGroup bootNioEventLoopGroup;

    @Autowired
    @Qualifier("serverBootStrap")
    private ServerBootstrap serverBootStrap;

    private ChannelFuture channelFuture = null;

    @PostConstruct
    public void postConstruct() {
        try {
            channelFuture = serverBootStrap.bind(webSocketProperties.getPort()).sync();
            log.info("绑定端口成功：" + webSocketProperties.getPort());
        } catch (InterruptedException e) {
            log.error("服务器绑定端口失败：" + webSocketProperties.getPort());
            workerNioEventLoopGroup.shutdownGracefully();
            bootNioEventLoopGroup.shutdownGracefully();
        }
    }

    public void sendMessageToClient(String s) {
        if (channelFuture == null) {
            log.error("没有连接信息，无法发送消息");
        }
        channelFuture.channel().writeAndFlush(new TextWebSocketFrame(s));
    }

    @PreDestroy
    public void close() throws InterruptedException {
        log.info("关闭Netty服务器");
        workerNioEventLoopGroup.shutdownGracefully();
        bootNioEventLoopGroup.shutdownGracefully();
    }
}
