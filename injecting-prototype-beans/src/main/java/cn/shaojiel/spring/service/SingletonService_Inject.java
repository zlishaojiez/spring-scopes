package cn.shaojiel.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.inject.Provider;

/**
 * javax.inject API
 * The setup along with required dependencies javax.inject library
 * <p>
 * Use Provider interface to inject the prototype bean. For each method call, the prototypeServiceProvider.get() method returns a new instance of PrototypeBean.
 */
@Service
public class SingletonService_Inject implements SingletonService{

    @Autowired
    public Provider<PrototypeService> prototypeServiceProvider;

    @Override
    public String getDateTime() {

        return  prototypeServiceProvider.get().getDateTime();
    }
}
