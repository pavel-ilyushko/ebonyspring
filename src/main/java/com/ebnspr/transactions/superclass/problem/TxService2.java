package com.ebnspr.transactions.superclass.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxService2 extends TxService implements ITxService {

    @Override
    public void callLikeInTransactionFromSuperClass() {
        TxUtils.checkTxStatus(this.getClass());
    }

    @Transactional
    public void callInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

    @Override
    public void callLikeInTransactionFromInterface() {
        TxUtils.checkTxStatus(this.getClass());
    }
}
