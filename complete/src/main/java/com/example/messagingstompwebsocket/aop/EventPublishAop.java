package com.example.messagingstompwebsocket.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class EventPublishAop implements Ordered {
    @Pointcut(value = "@annotation(com.example.messagingstompwebsocket.annotation.EventPublish)")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        Object proceed = null;
        try {
            log.info("事件发布准备开始");
            proceed = pjp.proceed();
            log.info(String.format("事件发布：%s", proceed));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
