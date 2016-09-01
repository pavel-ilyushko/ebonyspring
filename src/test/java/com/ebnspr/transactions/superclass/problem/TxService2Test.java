package com.ebnspr.transactions.superclass.problem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.transactions.superclass.problem.Config.class)
public class TxService2Test {

    @Autowired
    private TxService2 txService2;

    @Test
    public void callInTransaction() {
        // the method is called on a cglib proxy, thus in transaction
        txService2.callInTransaction();
    }

    @Test
    public void callLikeInTransaction() {
        // the callLikeInTransaction is an overriden one.
        // the @Transactional is set on super class' method
        // and the subclass' one does not inherit it
        txService2.callLikeInTransaction();
    }
}