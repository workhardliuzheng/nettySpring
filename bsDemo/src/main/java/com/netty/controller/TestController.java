package com.netty.controller;

import com.netty.client.ClientBoot;
import com.netty.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ClientBoot clientBoot;

    @RequestMapping("/test")
    public String test(@RequestParam("test") String test) throws InterruptedException {
        Message message = new Message();
        test = test + '\n';
        byte[] bytes = test.getBytes();
        message.setContent(bytes);
        message.setLen(bytes.length);
        clientBoot.sendMsg(message);
        return "搜到的消息是：" + test;
    }
}
