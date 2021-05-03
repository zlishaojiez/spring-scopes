package cn.shaojiel.spring.service.scope_proxy;

import cn.shaojiel.spring.service.PrototypeService;
import cn.shaojiel.spring.service.SingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingletonService_ScopeProxy implements SingletonService {

    @Autowired
    private PrototypeService_ScopeProxy prototypeService;

    @Override
    public String getDateTime() {
        return  prototypeService.getDateTime();
    }
}
