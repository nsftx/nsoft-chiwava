package com.nsoft.chiwava.debezium.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a potential candidate for event consumption
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
@AllArgsConstructor
public class Candidate {
    @Getter
    private String database;
    @Getter
    private String table;
    @Getter
    private OperationType operation;
}
