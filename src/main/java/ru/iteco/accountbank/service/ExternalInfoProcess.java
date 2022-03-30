package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;
import ru.iteco.accountbank.model.annotation.CheckRequest;

@Slf4j
@Lazy
@Component
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    @CheckRequest
    @Override
    public boolean run(ExternalInfo externalInfo) {

        if (id.equals(externalInfo.getId())) {
            throw new RuntimeException("Process with " + externalInfo + " not need");
        }
        log.info("Process with {}", externalInfo);
        return true;

    }
}
