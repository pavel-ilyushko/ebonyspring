package com.ebnspr.transactions.proxy.problem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.transactions.proxy.problem.Config.class)
public class TxServiceTest {
    @Autowired
    private TxService txService;

    @Test
    public void callInTransaction() {
        // the method is called on a cglib proxy, thus in transaction
        txService.callInTransaction();
    }

    @Test
    public void callLikeInTransactionNoProxy() {
        // the method is called on cglib proxy and then calls another method on 'this'
        // thus no tx interception takes place
        txService.callLikeInTransactionNoProxy();
    }

    @Test
    public void callLikeInTransactionNonPublic() {
        // even though a method is called on a cglib proxy,
        // the @Transactional annotated method is not proxied
        // because it has no public access (private-package in this example)
        txService.callLikeInTransactionNonPublic();
    }

    @Test
    public void callLikeInTransactionFinal() {
        // even though a method is called on a cglib proxy,
        // the @Transactional annotated method is not proxied
        // because it is final
        txService.callLikeInTransactionFinal();
    }

}