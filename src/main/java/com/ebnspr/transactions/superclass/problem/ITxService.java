package com.ebnspr.transactions.superclass.problem;

import org.springframework.transaction.annotation.Transactional;

public interface ITxService {

    @Transactional
    void callLikeInTransactionFromInterface();
}
