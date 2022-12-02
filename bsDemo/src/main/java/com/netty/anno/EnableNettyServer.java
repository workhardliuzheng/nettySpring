package com.netty.anno;

import com.netty.server.ServerBoot;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(ServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableNettyServer {
}
