package com.ebnspr.transactions.superclass2.problem;

import com.ebnspr.transactions.utils.TxUtils;
import org.springframework.transaction.annotation.Transactional;

public class TxServiceChild extends TxServiceSuper implements ITxService {

    @Override
    public void callLikeInTransactionFromInterface() {
        TxUtils.checkTxStatus(this.getClass());
    }
}
