package com.ebnspr.di.constructor.circular.solution1;

import com.ebnspr.di.constructor.circular.A;
import com.ebnspr.di.constructor.circular.B;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.ebnspr.di.constructor.circular")
public class CircularDependencyCtrSolutionConfig {

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
