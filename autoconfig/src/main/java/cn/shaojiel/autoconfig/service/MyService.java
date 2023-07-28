package cn.shaojiel.autoconfig.service;

import cn.shaojiel.autoconfig.properties.MyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyService {

    @Autowired
    private MyProperties properties;

    public void logProperties() {
        log.info("name = {}", properties.getName());
    }
}
