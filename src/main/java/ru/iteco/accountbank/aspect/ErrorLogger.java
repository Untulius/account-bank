package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ErrorLogger {

    @AfterThrowing(value = "checkAllMethods()", throwing = "exception")
    public void afterAllMethodsAdvice(JoinPoint joinPoint, Exception exception) {
        log.info("Method <<{}>> end with Exception: {}", joinPoint.getSignature().toShortString(), exception.toString());

    }

    @Pointcut("execution(* *(..))")
    public void checkAllMethods() {
    }
}
