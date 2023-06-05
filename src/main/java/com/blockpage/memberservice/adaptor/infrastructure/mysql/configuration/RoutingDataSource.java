package com.blockpage.memberservice.adaptor.infrastructure.mysql.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("읽기 전용 DB 연결");
            return "slave";
        } else {
            log.info("쓰기 전용 DB 연결");
            return "master";
        }
    }
}
