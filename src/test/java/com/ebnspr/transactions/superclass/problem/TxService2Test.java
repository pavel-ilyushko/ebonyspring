package com.ebnspr.transactions.superclass.problem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.transactions.superclass.problem.Config.class)
public class TxService2Test {

    @Autowired
    private TxService2 txService2;

    @Autowired
    @Qualifier("txService3")
    private ITxService txService3;

    @Test
    public void callInTransaction() {
        // the method is called on a cglib proxy, thus in transaction
        txService2.callInTransaction();
    }

    @Test
    public void callLikeInTransactionFromSuperClass() {
        txService2.callLikeInTransactionFromSuperClass();
    }

    @Test
    public void callLikeInTransactionFromInterfaceOnConcreteClass() {
        txService2.callLikeInTransactionFromInterface();
    }

    @Test
    public void callLikeInTransactionFromInterface() {
        txService3.callLikeInTransactionFromInterface();
    }
}