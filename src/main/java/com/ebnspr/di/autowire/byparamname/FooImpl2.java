package com.ebnspr.di.autowire.byparamname;

import org.springframework.stereotype.Component;

@Component
public class FooImpl2 implements FooInterface {
    public String sayHi() {
        return "Hi2";
    }
}
