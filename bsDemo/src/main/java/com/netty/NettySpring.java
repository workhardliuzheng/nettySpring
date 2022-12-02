package com.netty;

import com.netty.anno.EnableNettyClient;
import com.netty.anno.EnableNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableNettyClient
@EnableNettyServer
public class NettySpring {
    public static void main(String[] args) {
        SpringApplication.run(NettySpring.class, args);
    }
}
