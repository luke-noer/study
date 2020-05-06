package com.example.websocket.schedule;

import com.example.websocket.cache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 方法：
 *
 * @Author：Luke.noer
 * @Datetime: 2020/5/4 22:14
 */
@Component
public class Job {


    @Autowired
    SimpMessagingTemplate template;

//    @Scheduled(cron = "0/30 * * * * ?")
//    public void sendMessage() throws InterruptedException {
//        System.out.println("schedule-job");
//        List<String> list = UserCache.listUser();
//        if(list.size()>0) {
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println("send to :" + list.get(i));
//                template.convertAndSendToUser(list.get(i),"/ptp","来了");
//            }
//        }
//    }

}
