package com.ebnspr.di.constructor.circular.problem;


import com.ebnspr.di.constructor.circular.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * This test should fail during context loading because of circular dependency in constructor.
 * TODO: find out why java.lang.NoClassDefFoundError: org.springframework.beans.FatalBeanException
 * is thrown even though debugging showed that the original exception was java.lang.StackOverflowError
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.di.constructor.circular.problem.CircularDependencyCtrProblemConfig.class)
public class CircularDependencyCtrProblemConfigTest {

    @Autowired
    private A a;

    @Test
    public void circularDependencyInConstructorInjection() {
        final String hi = a.getB().sayHi();
        assertThat(hi, equalTo("Hi"));
    }
}