package com.example.messagingstompwebsocket.service;


import com.example.messagingstompwebsocket.EventVO;
import com.example.messagingstompwebsocket.annotation.EventPublish;
import com.example.messagingstompwebsocket.annotation.WebSocketScheduled;
import com.example.messagingstompwebsocket.util.MySpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Future;

@Component
@Slf4j
public class MyScheduledService {


    private final MySpringUtil mySpringUtil;
    @Autowired
    private ApplicationContext applicationContext;

    public MyScheduledService(MySpringUtil mySpringUtil) {
        this.mySpringUtil = mySpringUtil;
    }

    /**
     * PostConstruct 不可以SpringUtils.getBean 调用Async
     */
    @PostConstruct
    public void initTask() {
//        MyScheduledService bean = MySpringUtil.getBean(MyScheduledService.class);
//        bean.task("abc");
//        log.info("测试同步异步");
    }

    @WebSocketScheduled(delay = 1000)
    @Async("asyncThreadTaskExecutor")
    @EventPublish
    public String task(String flag) {
        log.info("进入任务");
        int i = new Random().nextInt(1000);
        String format = String.format("task value is %s,flag is %s", i, flag);
        log.info(String.format("com.example.messagingstompwebsocket.service.MyScheduledService.task 结果 : %s", format));
        applicationContext.publishEvent(new EventVO(this, format));
        return format;
    }
}
