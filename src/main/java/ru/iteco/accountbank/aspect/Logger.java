package ru.iteco.accountbank.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class Logger {

    @Before("checkMethodsInService()")
    public void beforeAllMethodsAdvice(JoinPoint joinPoint) {
        log.info("Start method <<{}>>", joinPoint.getSignature().toShortString());
    }

    @After("checkMethodsInService()")
    public void afterAllMethodsAdvice(JoinPoint joinPoint) {
        log.info("End of method <<{}>>", joinPoint.getSignature().toShortString());

    }

    @Pointcut("within(ru.iteco.accountbank.service.*)")
    public void checkMethodsInService() {
    }

}
