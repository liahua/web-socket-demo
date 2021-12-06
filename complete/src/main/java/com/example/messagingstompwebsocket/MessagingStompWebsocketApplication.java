package com.example.messagingstompwebsocket;

import cn.hutool.extra.spring.SpringUtil;
import com.example.messagingstompwebsocket.util.MySpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync(order = 0)
public class MessagingStompWebsocketApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MessagingStompWebsocketApplication.class, args);
		MySpringUtil.set(run);
	}
}
