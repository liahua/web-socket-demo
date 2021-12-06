package com.example.messagingstompwebsocket.service;

import com.example.messagingstompwebsocket.EventVO;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class TaskService {
    @EventListener
    public Object listener(EventVO str) {
        return null;
    }
}
