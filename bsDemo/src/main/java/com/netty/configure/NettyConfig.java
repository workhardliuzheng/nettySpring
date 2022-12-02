package com.netty.configure;

import com.netty.client.handler.ClientChannelInitializer;
import com.netty.server.handler.ServerChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class NettyConfig {
    @Autowired
    private NettyProperties nettyProperties;

    @Bean("boosGroup")
    public NioEventLoopGroup boosEventGroup() {
        return new NioEventLoopGroup(nettyProperties.getBoss());
    }

    @Bean("workerGroup")
    public NioEventLoopGroup workerEventGroup() {
        return new NioEventLoopGroup(nettyProperties.getWorker());
    }

    @Bean("serverBootstrap")
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosEventGroup(), workerEventGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,nettyProperties.getTimeout())
                .childHandler(new ServerChannelInitializer());
                //.handler(new ServerHandler());
        return serverBootstrap;
    }

    @Bean("clientBootStrap")
    public Bootstrap clientBootStrap() {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(nettyProperties.getBoss());
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer())
                .option(ChannelOption.SO_KEEPALIVE, true);
        return bootstrap;
    }
}
