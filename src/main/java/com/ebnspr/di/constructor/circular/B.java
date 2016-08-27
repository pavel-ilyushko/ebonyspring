package com.ebnspr.di.constructor.circular;

/**
 * Created by kiwi on 8/27/2016.
 */
public class B {

    private A a;

    public  B(A a) {
        this.a = a;
    }

    public String sayHi() {
        return "Hi";
    }

    public A getA() {
        return a;
    }
}
