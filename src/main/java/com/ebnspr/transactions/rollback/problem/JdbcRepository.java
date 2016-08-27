package com.ebnspr.transactions.rollback.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: of course, transactional annotations should be in service, not in repository
 */
@Transactional
public class JdbcRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createDatabase() {
        jdbcTemplate.execute("drop table TX_TABLE if exists;");
        jdbcTemplate.execute("create table TX_TABLE (name varchar(100));");
    }

    public Integer getNumberOfRows() {
        return jdbcTemplate.queryForObject("select count(*) from TX_TABLE", Integer.class);
    }

    public String getRow() {
        return jdbcTemplate.queryForObject("select * from TX_TABLE", String.class);
    }

    public void insertRow() {
        jdbcTemplate.execute("insert into TX_TABLE VALUES ('tx_required')");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRowInNewTx() {
        jdbcTemplate.execute("insert into TX_TABLE VALUES ('tx_requires_new')");
    }
}
