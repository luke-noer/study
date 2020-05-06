package com.example.websocket.domain;

/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/4 11:30
 */
public class HelloMessage {

    private String name;

    private String message;

    private String sendTo;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "HelloMessage{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", sendTo='" + sendTo + '\'' +
                '}';
    }
}
