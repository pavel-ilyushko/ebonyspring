package com.ebnspr.di.autowire.byparamname;

import org.springframework.stereotype.Component;

@Component
public class FooImpl1 implements FooInterface {
    public String sayHi() {
        return "Hi1";
    }
}
