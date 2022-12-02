package com.netty.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer len;
    private byte[] content;

    public Message(Object object) {
        content = JSONObject.toJSONString(object).getBytes(UTF_8);
        len = content.length;
    }
}
