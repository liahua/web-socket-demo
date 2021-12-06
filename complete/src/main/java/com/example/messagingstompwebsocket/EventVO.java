package com.example.messagingstompwebsocket;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventVO extends ApplicationEvent {
    private String str;

    public EventVO(Object source, String str) {
        super(source);
        this.str = str;
    }
}
