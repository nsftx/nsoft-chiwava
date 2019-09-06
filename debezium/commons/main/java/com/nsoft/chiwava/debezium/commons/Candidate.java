package com.nsoft.chiwava.debezium.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Candidate {
    @Getter
    private String database;
    @Getter
    private String table;
    @Getter
    private OperationType operation;
}
