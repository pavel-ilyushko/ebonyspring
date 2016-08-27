package com.ebnspr.di.autowire.byparamname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AutowireByParameterName {
    private FooInterface fooImpl1;

    @Autowired
    public AutowireByParameterName(FooInterface fooImpl1) { // the implementation is chosen based on parameter name!
        this.fooImpl1 = fooImpl1;                           // when the code is compiled with 'g:none' option,
                                                            // the parameter name info is gone!!
    }

    String talkToFoo() {
        return fooImpl1.sayHi();
    }
}
