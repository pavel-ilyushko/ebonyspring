package com.ebnspr.di.constructor.circular;

import com.ebnspr.di.constructor.circular.solution1.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by kiwi on 8/27/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class CircularTest {

    @Autowired
    private A a;

    @Test
    public void injectBtoA() {
        final String hi = a.getB().sayHi();
        assertThat(hi, equalTo("Hi"));
    }
}