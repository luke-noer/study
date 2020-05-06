package com.example.websocket.service;

import com.example.websocket.cache.UserCache;
import com.example.websocket.domain.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/5 9:56
 */
@Service
public class SendMessageService {

    @Autowired
    SimpMessagingTemplate template;

    public void sendToAll(HelloMessage message) {
        template.convertAndSend("/topic/public",message);
    }

    public void sendToUser(HelloMessage message) {
        template.convertAndSendToUser(message.getSendTo(),"/ptp",message);
    }

    public void showUsers() {
        template.convertAndSend("/topic/users",UserCache.listUser());
    }

    public void invalidateUser(HelloMessage message) {
        message.setMessage("no this user!");
        template.convertAndSendToUser(message.getName(),"/self",message);
    }
}
