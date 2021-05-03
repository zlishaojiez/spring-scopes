package cn.shaojiel.spring.controller;

import cn.shaojiel.spring.service.SingletonService;
import cn.shaojiel.spring.service.SingletonService_ApplicationContext;
import cn.shaojiel.spring.service.SingletonService_Issue;
import cn.shaojiel.spring.service.SingletonService_Method;
import cn.shaojiel.spring.service.SingletonService_ObjectFactory;
import cn.shaojiel.spring.service.scope_proxy.SingletonService_ScopeProxy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private SingletonService_Issue singletonService_issue;

    @Autowired
    private SingletonService_ApplicationContext singletonService_applicationContext;

    @Autowired
    private SingletonService_Method singletonService_method;

    @Autowired
    private SingletonService_ScopeProxy singletonService_scopeProxy;

    @Autowired
    private SingletonService_ObjectFactory singletonService_objectFactory;


    @GetMapping("/issue")
    public List<String> getDataTime_Issue() {
        return this.getDateTime(singletonService_issue);
    }


    @GetMapping("/applicationContext")
    public List<String> getDataTime_ApplicationContext() {
        return this.getDateTime(singletonService_applicationContext);
    }

    @GetMapping("/lookup")
    public List<String> getDataTime_Method() {
        return this.getDateTime(singletonService_method);
    }

    @GetMapping("/scope_proxy")
    public List<String> getDataTime_ScopeProxy() {
        return this.getDateTime(singletonService_scopeProxy);
    }

    @GetMapping("/object_factory")
    public List<String> getDataTime_ObjectFactory() {
        return this.getDateTime(singletonService_objectFactory);
    }

    @SneakyThrows
    private List<String> getDateTime(SingletonService singletonService) {
        String first = singletonService.getDateTime();
        Thread.sleep(1000);
        String second = singletonService.getDateTime();

        return List.of(first, second);
    }
}
