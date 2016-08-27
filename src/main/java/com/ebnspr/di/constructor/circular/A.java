package com.ebnspr.di.constructor.circular;

/**
 * Created by kiwi on 8/27/2016.
 */
class A {
    private B b;

    A(B b) {
        this.b = b;
    }

    B getB() {
        return b;
    }
}
