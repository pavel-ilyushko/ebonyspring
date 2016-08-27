package com.ebnspr.di.autowire.byparamname;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class AutowireByParameterNameTest {

    @Autowired
    private AutowireByParameterName parameterDiByImplicitName;

    @Test
    public void resolveFooImplementation() {
        final String hi = parameterDiByImplicitName.talkToFoo();
        assertThat(hi, equalTo("Hi1"));

    }
}