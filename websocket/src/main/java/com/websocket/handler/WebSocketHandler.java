package com.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Random random = new Random();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("收到消息：" + msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("收到消息，服务器状态是：" + getServerState()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded被调用：" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded被调用：" + ctx.channel().id().asShortText());
        System.out.println(sdf.format(new Date()) + "  【服务器】用户已连接！");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved被调用：" + ctx.channel().id().asLongText());
        System.out.println(sdf.format(new Date()) + "  【服务器】用户已断开连接");
    }

    private String getServerState() {
        int state = random.nextInt() % 3;
        switch (state) {
            case 0:
                return "特别强";
            case 1:
                return "正常";
            case 2:
                return "不正常";
            default:
                return "";
        }
    }
}
