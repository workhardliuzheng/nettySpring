package com.websocket.anno;

import com.websocket.server.ServerBoot;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(ServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableWebSocketServer {
}
