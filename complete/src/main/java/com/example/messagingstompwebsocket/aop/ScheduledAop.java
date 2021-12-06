package com.example.messagingstompwebsocket.aop;

import com.example.messagingstompwebsocket.annotation.WebSocketScheduled;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class ScheduledAop implements Ordered {
    ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10, getThreadFactory());

    private ThreadFactory getThreadFactory() {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat("scheduled-pool-%d");
        return threadFactoryBuilder.build();
    }


    @Pointcut(value = "@annotation(com.example.messagingstompwebsocket.annotation.WebSocketScheduled)")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        log.info("进入定时任务切面");
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
            WebSocketScheduled annotation = method.getAnnotation(WebSocketScheduled.class);
            int delay = annotation.delay();
            pool.scheduleWithFixedDelay(() -> {
                try {
                    log.info("本轮定时任务开始");
                    pjp.proceed();
                    log.info("本轮定时任务结束");
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            },0,delay, TimeUnit.MILLISECONDS);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
