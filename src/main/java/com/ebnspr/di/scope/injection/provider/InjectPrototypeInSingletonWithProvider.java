package com.ebnspr.di.scope.injection.provider;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * To insert a prototype bean into a singleton one it's better to user the Provider.
 * Thus we can control when exactly a prototype is recreated by calling Provider.get().
 * This maybe useful if we want to create a prototype, store some state into it, reuse it,
 * and then finally discard it.
 */
public class InjectPrototypeInSingletonWithProvider {

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
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

    @Component
    static class SingletonWithProvider {

        @Autowired
        javax.inject.Provider<Prototype> provider;

    }

    @ComponentScan("com.ebnspr.di.scope.injection.provider")
    @Configuration
    static class Config {}

    @Test
    public void shouldGetValueFromPrototype() throws Exception {
        //given
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        //when
        final SingletonWithProvider singletonWithProvider = ctx.getBean(SingletonWithProvider.class);

        final Prototype prototype1 = singletonWithProvider.provider.get();
        final Prototype prototype2 = singletonWithProvider.provider.get();

        //then
        assertTrue("New instance expected", prototype1 != prototype2);

        prototype1.setX(1);
        assertThat(prototype1.getX(), equalTo(1));

        prototype1.setX(2);
        assertThat(prototype1.getX(), equalTo(2));


    }

}
