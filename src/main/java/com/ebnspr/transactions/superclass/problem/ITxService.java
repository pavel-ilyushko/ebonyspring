package com.ebnspr.transactions.superclass.problem;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pilyushko on 01.09.2016.
 */
public interface ITxService {

    @Transactional
    void callLikeInTransactionFromInterface();
}
