package cn.shaojiel.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describe the prototype bean injection problem
 * <p>
 * Both beans were initialized only once, at the startup of the application context.
 */
@Service
public class SingletonService_Issue implements SingletonService{

    @Autowired
    private PrototypeService prototypeService;

    @Override
    public String getDateTime() {
        return  prototypeService.getDateTime();
    }
}
