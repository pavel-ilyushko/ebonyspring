package com.ebnspr.transactions.superclass.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxService2 extends TxService {

    @Override
    public void callLikeInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

    @Transactional
    public void callInTransaction() {
        TxUtils.checkTxStatus(this.getClass());
    }

}
