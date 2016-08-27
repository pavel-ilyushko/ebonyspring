package com.ebnspr.di.constructor.circular;

import org.springframework.context.annotation.*;

/**
 * Created by kiwi on 8/27/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.ebnspr.di.constructor.circular")
class Config {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public B b() {
        return new B(a());
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public A a() {
        return new A(b());
    }
}
