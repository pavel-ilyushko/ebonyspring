package com.ebnspr.di.scope.injection.supplier;


import com.ebnspr.di.scope.injection.lookup.InjectPrototypeInSingletonWithLookup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * To insert a prototype bean into a singleton one can use a java.util.function.Supplier.
 * Thus we can control when exactly a prototype is recreated by calling Supplier.get().
 * This maybe useful if we want to create a prototype, store some state into it, reuse it,
 * and then finally discard it.
 */
public class InjectPrototypeInSingletonWithSupplier {

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

    static class Singleton {
        @Autowired
        private Supplier<Prototype> prototypeSupplier;

        public Prototype getPrototype() {
            return prototypeSupplier.get();
        }

        public Integer calculateX() {
            final Prototype prototype = prototypeSupplier.get();
            prototype.setX(1);
            Integer result = prototype.getX();
            return result;
        }

    }

    @ComponentScan("com.ebnspr.di.scope.injection.supplier")
    @Configuration
    static class Config {

        @Bean
        public Singleton singleton() {
            return new Singleton();
        }

        @Bean
        @Scope(BeanDefinition.SCOPE_PROTOTYPE)
        public Prototype prototype() {
            return new Prototype();
        }

        @Bean
        public Supplier<Prototype> prototypeSupplier() {
            return this::prototype;
        }
    }

    @Test
    public void shouldGetValueFromPrototype() throws Exception {
        //given
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        //when
        final Singleton singleton = ctx.getBean(Singleton.class);

        final Prototype prototype1 = singleton.getPrototype();
        final Prototype prototype2 = singleton.getPrototype();

        //then
        assertTrue("New instance expected", prototype1 != prototype2);

        final Integer x = singleton.calculateX();
        assertThat(x, equalTo(1));
    }

}
