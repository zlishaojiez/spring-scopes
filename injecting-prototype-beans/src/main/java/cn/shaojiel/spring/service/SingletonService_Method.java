package cn.shaojiel.spring.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * Method Injection
 * Method injection with the @Lookup annotation
 * <p>
 * Spring will override the getPrototypeBean() method annotated with @Lookup. It then registers the bean into the application context. Whenever we request the getPrototypeBean() method, it returns a new PrototypeBean instance.
 * <p>
 * It will use CGLIB to generate the bytecode responsible for fetching the PrototypeBean from the application context.
 */
@Service
public class SingletonService_Method implements SingletonService{

    @Lookup
    public PrototypeService getPrototypeService() {
        return null;
    }

    @Override
    public String getDateTime() {

        return  getPrototypeService().getDateTime();
    }
}
