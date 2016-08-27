package com.ebnspr.transactions.problem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.ebnspr.transactions.problem.Config.class)
@Transactional
public class TransactionProblemTest {

    @Autowired
    JdbcRepository jdbcRepository;

    @Before
    public void setUp(){
        jdbcRepository.createDatabase();
        assertThat(jdbcRepository.getNumberOfRows(), equalTo(0));
    }

    @Test
    public void insertRow(){
        jdbcRepository.insertRow();
        assertThat(jdbcRepository.getNumberOfRows(), equalTo(1));
        assertThat(jdbcRepository.getRow(), equalTo("tx_required"));
    }

    @Test
    public void insertRowInNewTransaction(){
        jdbcRepository.insertRowInNewTx();
        assertThat(jdbcRepository.getNumberOfRows(), equalTo(1));
        assertThat(jdbcRepository.getRow(), equalTo("tx_requires_new"));
    }

    @After
    public void afterTestCompletedButTransactionNotRolledBackBySpring() {
        assertThat(jdbcRepository.getNumberOfRows(), equalTo(1));
    }

    /**
     * After Spring has rolled back normal transactions (like REQUIRED), those created
     * with propagation = REQUIRES_NEW (in a new transaction) are not rolled back
     */
    @AfterTransaction
    public void afterTestCompletedAndTransactionRolledBackBySpring() {
        assertThat(jdbcRepository.getNumberOfRows(), equalTo(0)); // fails for REQUIRES_NEW tx
    }



}