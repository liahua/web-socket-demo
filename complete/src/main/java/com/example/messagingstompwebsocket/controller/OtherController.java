package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.service.MyScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OtherController {
    @Autowired
    private MyScheduledService myScheduledService;

    @RequestMapping("/test-asy")
    public void testAsy() {
        myScheduledService.task("bac");
        log.info("测试");
    }
}
