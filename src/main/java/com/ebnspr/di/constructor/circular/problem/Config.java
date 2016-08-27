package com.ebnspr.di.constructor.circular.problem;

import com.ebnspr.di.constructor.circular.A;
import com.ebnspr.di.constructor.circular.B;
import org.springframework.context.annotation.*;

/**
 * Created by kiwi on 8/27/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.ebnspr.di.constructor.circular")
class Config {

    @Bean
    public B b() {
        return new B(a());
    }

    @Bean
    public A a() {
        return new A(b());
    }
}
