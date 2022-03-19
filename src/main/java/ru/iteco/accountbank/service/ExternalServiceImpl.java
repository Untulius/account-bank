package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

@Slf4j
@Component
public class ExternalServiceImpl implements ExternalService {

    private final HashMap<Integer, ExternalInfo> externalInfoHashMap = new HashMap<>();

    public ExternalServiceImpl() {
    }

    @PostConstruct
    public void init(){
        externalInfoHashMap.put(1, new ExternalInfo(1, null));
        externalInfoHashMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoHashMap.put(3, new ExternalInfo(3, "info"));
        externalInfoHashMap.put(4, new ExternalInfo(4, "information"));
    }

    public ExternalInfo getExternalInfo(Integer id){
        log.info("getExternalInfo({}) = {}", id, externalInfoHashMap.get(id));
        return externalInfoHashMap.get(id);
    }

    @PreDestroy
    public void destroy(){
        log.info("externalInfoHashMap before clear: {}", externalInfoHashMap);
        externalInfoHashMap.clear();
        log.info("externalInfoHashMap after clear: {}", externalInfoHashMap);
    }
}
