package com.netty.server.handler;

import com.netty.entity.Cmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import static io.netty.util.CharsetUtil.UTF_8;

@Slf4j
public class CmdHandler extends SimpleChannelInboundHandler<Cmd> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
        log.info("收到的命令信息是：name:{}, args:{}", msg.getName().toString(UTF_8), msg.getArgs().toString(UTF_8));
    }
}
