package com.example.websocket.controller;

import com.example.websocket.cache.UserCache;
import com.example.websocket.domain.Greeting;
import com.example.websocket.domain.HelloMessage;
import com.example.websocket.domain.User;
import com.example.websocket.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;


/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/4 11:34
 */
@Controller
public class GreetingController {

    static String ALL_USER = "all";

    @Autowired
    SendMessageService sendMessageService;

    /**
     * 新登陆用户-注册
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/register")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("register_user:"+message.getName());
        if(null==UserCache.getUser(message.getName())) {
            User user = new User(message.getName());
            UserCache.addUser(message.getName(), user);
            sendMessageService.showUsers();
            return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        }else {
            return null;
        }
    }

    /**
     * 用户发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/message")
    public void sendMessage(HelloMessage message) throws Exception {
        System.out.println("message:"+message.toString());
        String targetUser = message.getSendTo();
        if(ALL_USER.equals(targetUser) || StringUtils.isEmpty(targetUser)){
            message.setSendTo("all");
            sendMessageService.sendToAll(message);
        }else {
            if (null == UserCache.getUser(targetUser)) {
                sendMessageService.invalidateUser(message);
            }
            sendMessageService.sendToUser(message);
        }

    }







}