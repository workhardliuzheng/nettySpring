package com.netty.client.handler;

import com.netty.entity.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
    /**
     * 服务端上线的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}连上了服务器",ctx.channel().remoteAddress());
    }

    /**
     * 服务端掉线的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}断开了服务器",ctx.channel().remoteAddress());
        ctx.fireChannelInactive();
    }


    /**
     * 读取服务端消息
     * @param channelHandlerContext
     * @param messageBean
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message messageBean) throws Exception {
        log.info("来自服务端的消息:{}",new String(messageBean.getContent(), CharsetUtil.UTF_8));
        channelHandlerContext.channel().close();
    }
}
