package com.example.websocket.domain;

/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/4 11:31
 */
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}