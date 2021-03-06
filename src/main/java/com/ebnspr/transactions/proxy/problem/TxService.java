package com.ebnspr.transactions.proxy.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxService {

    @Transactional
    public void callInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

    public void callLikeInTransactionNoProxy() {
        callInTransaction();
    }

    @Transactional
    void callLikeInTransactionNonPublic() {
        TxUtils.checkTxStatus(this.getClass());
    }

    @Transactional
    public final void callLikeInTransactionFinal() {
        TxUtils.checkTxStatus(this.getClass());
    }
}
