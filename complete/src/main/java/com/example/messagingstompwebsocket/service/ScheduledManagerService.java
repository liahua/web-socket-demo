package com.example.messagingstompwebsocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class ScheduledManagerService {

    @Autowired
    private MyScheduledService myScheduledService;

    @PostConstruct
    public void init() {
        myScheduledService.task("def");
        log.info("任务启动");
    }
}
