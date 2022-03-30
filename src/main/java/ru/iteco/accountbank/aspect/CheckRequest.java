package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

@Aspect
@Slf4j
@Component
public class CheckRequest {

    @Value("${id-not-process}")
    private Integer id;


    @Around("methodsWithCheckRequestAnnotaion(externalInfo)")
    public Object checkArgAndConfig(ProceedingJoinPoint proceedingJoinPoint, ExternalInfo externalInfo) throws Throwable {
        log.info("checkArgAndConfig for method <<{}>> with arg {}", proceedingJoinPoint.getSignature().toShortString(), externalInfo);
        if (id.equals(externalInfo.getId())) {
            throw new RuntimeException("ExternalInfo.getId == config.id-not-process");
        } else {
            log.info("Proceed method <<{}>>", proceedingJoinPoint.getSignature().toShortString());
            return proceedingJoinPoint.proceed();
        }

    }


    @Pointcut("@annotation(ru.iteco.accountbank.model.annotation.CheckRequest) && args(externalInfo, ..)")
    public void methodsWithCheckRequestAnnotaion(ExternalInfo externalInfo) {
    }

}
