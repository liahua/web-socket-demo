package com.example.messagingstompwebsocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ScheduleService {

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    static LinkedBlockingQueue<String> list =new LinkedBlockingQueue<>();


    @Scheduled(fixedDelay = 500)
    public void scheduleTask1() {

        brokerMessagingTemplate.convertAndSend("/topic/first", getMessage("task1-"));
    }

    @Scheduled(fixedDelay = 500)
    public void scheduleTask2() {
        brokerMessagingTemplate.convertAndSend("/topic/second", getMessage("task2-"));
    }

    private Object getMessage(String str) {
        int i = new Random().nextInt(1000);
        return str + i;
    }

}
