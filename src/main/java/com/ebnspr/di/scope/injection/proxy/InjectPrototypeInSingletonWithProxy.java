package com.ebnspr.di.scope.injection.proxy;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.junit.Assert.assertNull;

/**
 * To inject a prototype into a singleton bean,
 * we can declare the prototype as a proxied bean.
 *
 * The drawback is that each time a prototype's method is called,
 * a new instance of the prototype is created thus the previous state is discarded.
 */
public class InjectPrototypeInSingletonWithProxy {

    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Component
    static class PrototypeWithProxy {
        Integer x;

        public Integer getX() {
            System.out.println("x = " + this.x + " for instance: " + toString());
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
            System.out.println("x = " + this.x + " for instance: " + toString());
        }
    }

    @Service
    static class Singleton {
        @Autowired
        PrototypeWithProxy prototypeWithProxy;
    }

    @ComponentScan("com.ebnspr.di.scope.injection.proxy")
    @Configuration
    static class Config {}

    @Test
    public void shouldNotGetSameValueFromPrototype() throws Exception {
        //given
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        //when
        final Singleton singleton = ctx.getBean(Singleton.class);

        singleton.prototypeWithProxy.setX(1); // calling a method on proxy is done on a new object
        assertNull(singleton.prototypeWithProxy.getX()); // so getX() is called on a new prototype with x not set

    }
}
