package com.example.websocket.domain;

/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/5 21:42
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
