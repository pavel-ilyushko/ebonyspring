package com.ebnspr.transactions.superclass.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxService3 implements ITxService {

    @Transactional
    public void callInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

    @Override
    public void callLikeInTransactionFromInterface() {
        TxUtils.checkTxStatus(this.getClass());
    }
}
