package com.ebnspr.transactions.superclass2.problem;

import com.ebnspr.transactions.superclass2.problem.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.transactions.superclass2.problem.Config.class)
public class TxServiceChildTest {

    @Autowired
    private ITxService txServiceChild;

    @Test
    public void callLikeInTransactionFromSuperClass() {
        txServiceChild.callLikeInTransactionFromInterface();
    }
}