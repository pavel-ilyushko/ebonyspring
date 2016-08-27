package com.ebnspr.di.constructor.circular.solution;

import com.ebnspr.di.constructor.circular.A;
import com.ebnspr.di.constructor.circular.B;
import org.springframework.context.annotation.*;

/**
 * Created by kiwi on 8/27/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.ebnspr.di.constructor.circular")
public class Config {

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
