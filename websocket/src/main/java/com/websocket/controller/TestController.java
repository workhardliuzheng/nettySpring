package com.websocket.controller;

import com.websocket.server.ServerBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ServerBoot serverBoot;

    @GetMapping("/send/msg")
    public void sendMsg(@RequestParam("message") String message) {
        serverBoot.sendMessageToClient(message);
    }
}
