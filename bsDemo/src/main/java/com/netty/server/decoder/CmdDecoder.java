package com.netty.server.decoder;

import com.netty.entity.Cmd;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LineBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CmdDecoder extends LineBasedFrameDecoder {

    public CmdDecoder(int maxLength) {
        super(maxLength);
    }

    public CmdDecoder(int maxLength, boolean stripDelimiter, boolean failFast) {
        super(maxLength, stripDelimiter, failFast);
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) {
        try {
            ByteBuf decode = (ByteBuf)super.decode(ctx, buffer);
            if (decode == null) {
                return null;
            }
            int index = decode.indexOf(decode.readerIndex(), decode.writerIndex(), (byte) ' ');
            return new Cmd(decode.slice(decode.readerIndex(), index), decode.slice(index +1, decode.writerIndex() - index - 1));

        } catch (Exception e) {
            log.error("CmdDecoder解码失败，[]", e);
        }
        return null;
    }
}
