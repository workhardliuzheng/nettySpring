package com.netty.server;

import com.netty.configure.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
//@Lazy
public class ServerBoot {
    @Autowired
    @Qualifier("serverBootstrap")
    ServerBootstrap serverBootstrap;
    @Autowired
    @Qualifier("boosGroup")
    NioEventLoopGroup boosGroup;
    @Autowired
    @Qualifier("workerGroup")
    NioEventLoopGroup workerGroup;
    @Autowired
    NettyProperties holeNettyProperties;

    /**
     * 开机启动
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // 绑定端口启动
        serverBootstrap.bind(holeNettyProperties.getPort()).sync();
        log.info("启动Netty多端口服务器: {}",holeNettyProperties.getPort());
    }

    /**
     * 关闭线程池
     */
    @PreDestroy
    public void close() throws InterruptedException {
        log.info("关闭Netty服务器");
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
