package com.ebnspr.di.scope.injection.lookup;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * To inject a prototype into a singleton bean,
 * we can use a @Lookup annotation.
 */
public class InjectPrototypeInSingletonWithLookup {

    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    @Component
    static class Prototype {
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

        @Lookup
        public Prototype getPrototype() {
            // the actual prototype object will be created by CGLIB
            // each time this method is called
            return null;
        }

        public Integer calculateX() {
            final Prototype aProxy = getPrototype();
            aProxy.setX(1);
            Integer result = aProxy.getX();
            return result;
        }
    }

    @ComponentScan("com.ebnspr.di.scope.injection.lookup")
    @Configuration
    static class Config {}

    @Test
    public void shouldNotGetSameValueFromPrototype() throws Exception {
        //given
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        //when
        final Singleton singleton = ctx.getBean(Singleton.class);

        final Prototype prototype1 = singleton.getPrototype();
        final Prototype prototype2 = singleton.getPrototype();

        //then
        assertTrue("New instance expected", prototype1 != prototype2);

        prototype1.setX(1);
        assertThat(prototype1.getX(), equalTo(1));
    }
}
