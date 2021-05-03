package cn.shaojiel.spring.service;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ObjectFactory Interface
 *
 * Spring provides the ObjectFactory<T> interface to produce on demand objects of the given type
 * <p>
 * getObject() returns a brand new instance of PrototypeBean for each request.
 * <p>
 * Here, we have more control over initialization of the prototype.
 * <p>
 * Also, the ObjectFactory is a part of the framework; this means avoiding additional setup in order to use this option.
 */
@Service
public class SingletonService_ObjectFactory implements SingletonService{

    @Autowired
    private ObjectFactory<PrototypeService> prototypeServiceObjectFactory;

    @Override
    public String getDateTime() {
        PrototypeService prototypeService = prototypeServiceObjectFactory.getObject();

        return  prototypeService.getDateTime();
    }
}
