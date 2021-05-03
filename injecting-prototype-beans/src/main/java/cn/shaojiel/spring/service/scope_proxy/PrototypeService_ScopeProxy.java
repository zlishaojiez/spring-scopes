package cn.shaojiel.spring.service.scope_proxy;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Scoped Proxy
 * <p>
 * Here, we create a proxy object to wire the real object with the dependent one.
 * <p>
 * Each time the method on the proxy object is called, the proxy decides itself whether to create a new instance of the real object or reuse the existing one.
 * <p>
 * By default, Spring uses CGLIB library to directly subclass the objects.
 * To avoid CGLIB usage, we can configure the proxy mode with ScopedProxyMode.INTERFACES, to use the JDK dynamic proxy instead.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeService_ScopeProxy {

    private String dateTime = LocalDateTime.now().toString();

    public String getDateTime() {
        return dateTime;
    }
}
