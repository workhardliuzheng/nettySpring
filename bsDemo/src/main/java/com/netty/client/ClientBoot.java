package com.netty.client;

import com.netty.configure.NettyProperties;
import com.netty.entity.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClientBoot {
    @Autowired
    @Qualifier("clientBootStrap")
    private Bootstrap clientBootStrap;
    @Autowired
    private NettyProperties nettyProperties;

    /**
     * 主端口连接
     * @return
     * @throws InterruptedException
     */
    public Channel connect() throws InterruptedException {
        // 连接服务器
        ChannelFuture channelFuture = clientBootStrap.connect(nettyProperties.getHost(), nettyProperties.getPort()).sync();
        // 监听关闭
        return channelFuture.channel();
    }

    /**
     * 发送消息到服务器端
     * @return
     */
    public void sendMsg(Message message) throws InterruptedException {
        connect().writeAndFlush(Unpooled.copiedBuffer(message.getContent()));
    }

}
