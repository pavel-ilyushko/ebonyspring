package com.ebnspr.di.constructor.circular;

/**
 * Created by kiwi on 8/27/2016.
 */
class B {

    private A a;

    B(A a) {
        this.a = a;
    }

    String sayHi() {
        return "Hi";
    }

    A getA() {
        return a;
    }
}
