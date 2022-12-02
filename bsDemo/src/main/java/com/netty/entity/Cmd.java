package com.netty.entity;

import io.netty.buffer.ByteBuf;

public class Cmd {
    private final ByteBuf name;
    private final ByteBuf args;

    public Cmd(ByteBuf name, ByteBuf args) {
        this.name = name;
        this.args = args;
    }

    public ByteBuf getName() {
        return name;
    }

    public ByteBuf getArgs() {
        return args;
    }
}
