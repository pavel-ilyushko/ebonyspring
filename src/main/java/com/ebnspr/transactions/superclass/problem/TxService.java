package com.ebnspr.transactions.superclass.problem;

import org.springframework.transaction.annotation.Transactional;

public abstract class TxService {

    @Transactional
    public abstract void callLikeInTransaction();

}
