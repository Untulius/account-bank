package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

@Slf4j
@Lazy
@Component
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    @Override
    public boolean run(ExternalInfo externalInfo) {

        if (id.equals(externalInfo.getId())){
            log.info("Process with {} not need", externalInfo);
            return false;
        }

        log.info("Process with {}", externalInfo);
        return true;
    }
}
