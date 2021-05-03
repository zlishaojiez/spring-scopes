


# injecting-prototype-beans
  Injecting Prototype Beans into a Singleton Instance in Spring

  References:
  * [https://www.youtube.com/watch?v=-ebmi2hfvWE](https://www.youtube.com/watch?v=-ebmi2hfvWE)
  * [https://www.baeldung.com/spring-inject-prototype-bean-into-singleton](https://www.baeldung.com/spring-inject-prototype-bean-into-singleton)

## Describe the prototype bean injection problem
   SingletonService_Issue.java

**Both beans were initialized only once**, at the startup of the application context.

## Resolve the prototype bean injection problem
   1. Injecting ApplicationContext
      
      SingletonService_ApplicationContext.java 

      **To achieve this, either use the @Autowire annotation or implement the ApplicationContextAware interface.**
      
      Every time the getPrototypeBean() method is called, a new instance of PrototypeBean will be returned from the ApplicationContext.
      
      **However, this approach has serious disadvantages.** It contradicts the principle of inversion of control, as we request the dependencies from the container directly.
      
      Also, we fetch the prototype bean from the applicationContext within the SingletonAppcontextBean class. **This means coupling the code to the Spring Framework.**
   

   2. Method Injection
      
      SingletonService_Method.java
            
      Method injection with the **@Lookup annotation**
      
      Spring will override the getPrototypeBean() method annotated with @Lookup. It then registers the bean into the application context. Whenever we request the getPrototypeBean() method, it returns a new PrototypeBean instance.
      
      **It will use CGLIB to generate the bytecode** responsible for fetching the PrototypeBean from the application context.
   

   3. javax.inject API
      
      SingletonService_Inject.java

      The setup along with required dependencies javax.inject library

      For Gradle:
      
      ```
      testCompile group: 'javax.inject', name: 'javax.inject', version: '1'
      ```
      
      For Maven:

      ```xml
      <dependency>
          <groupId>javax.inject</groupId>
          <artifactId>javax.inject</artifactId>
          <version>1</version>
      </dependency>
      ```
      Use Provider interface to inject the prototype bean. For each method call, the prototypeServiceProvider.get() method returns a new instance of PrototypeBean.


   4. Scoped Proxy
      
      PrototypeService_ScopeProxy.java
      SingletonService_ScopeProxy.java

      By default, Spring holds a reference to the real object to perform the injection. **Here, we create a proxy object to wire the real object with the dependent one.**

      Each time the method on the proxy object is called, the proxy decides itself whether to create a new instance of the real object or reuse the existing one.

      By default, Spring uses CGLIB library to directly subclass the objects. To avoid CGLIB usage, we can configure the proxy mode with ScopedProxyMode.INTERFACES, to use the JDK dynamic proxy instead.

   
   5. ObjectFactory Interface
      
      SingletonService_ObjectFactory.java

      Spring provides the ObjectFactory<T> interface to produce on demand objects of the given type

      getObject() returns a brand new instance of PrototypeBean for each request.
      
      Here, we have more control over initialization of the prototype.

      Also, the ObjectFactory is a part of the framework; this means avoiding additional setup in order to use this option.