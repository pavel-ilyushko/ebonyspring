package com.ebnspr.transactions.utils;

import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

public class TxUtils {

    public static void checkTxStatus(Class<?> clazz) {
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        LoggerFactory.getLogger(clazz).info("Is Transaction active? {}, readOnly? {}", active, readOnly);
        Assert.isTrue(active);
    }
}
