package com.ebnspr.transactions.proxy.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxService {

    @Transactional
    public void callInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

    public void callLikeInTransaction() {
        callInTransaction();
    }

    @Transactional
    void callLikeInTransaction2() {
        TxUtils.checkTxStatus(this.getClass());
    }

}
