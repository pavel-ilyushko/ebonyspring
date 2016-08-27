package com.ebnspr.di.constructor.circular;

/**
 * Created by kiwi on 8/27/2016.
 */
public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
}
