package com.netty.server.handler;

import com.netty.server.decoder.CmdDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;

@ChannelHandler.Sharable
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //pipeline.addLast(new MessageDecoder());
        ByteBuf byteBuf = Unpooled.copiedBuffer("_$".getBytes());
        pipeline.addLast(new CmdDecoder(1024, byteBuf));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new CmdHandler());
    }
}
