package com.example.log_prac;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("within(com.example.log_prac.controller.*)") // 패키지 범위 설정
    public void controller() {}

    @Before("controller()")
    public void logging(JoinPoint joinPoint) {
        logger.info("###Start request {}", joinPoint.getSignature().toShortString());
        Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .forEach(logger::info);
    }

    @AfterReturning(pointcut = "controller()", returning = "returnValue")
    public void logging2(JoinPoint joinPoint, Object returnValue) {
        logger.info("###End request {}", joinPoint.getSignature().toShortString());

        if (returnValue == null) return;

        logger.info("{}", returnValue.toString());
    }

    @AfterThrowing(pointcut = "controller()", throwing = "e")
    public void logging2(JoinPoint joinPoint, Exception e) {
        logger.error("###Occured error in request {}", joinPoint.getSignature().toShortString());
        logger.error(e.getMessage());
    }
}
