package cn.shaojiel.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Injecting ApplicationContext
 * To achieve this, either use the @Autowire annotation or implement the ApplicationContextAware interface
 * <p>
 * Every time the getPrototypeBean() method is called, a new instance of PrototypeBean will be returned from the ApplicationContext.
 * <p>
 * However, this approach has serious disadvantages. It contradicts the principle of inversion of control, as we request the dependencies from the container directly.
 * <p>
 * Also, we fetch the prototype bean from the applicationContext within the SingletonAppcontextBean class. This means coupling the code to the Spring Framework.
 */
@Service
public class SingletonService_ApplicationContext implements SingletonService{

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String getDateTime() {
        PrototypeService prototypeService = applicationContext.getBean(PrototypeService.class);

        return  prototypeService.getDateTime();
    }
}
