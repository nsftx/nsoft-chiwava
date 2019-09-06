package com.nsoft.chiwava.debezium.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum representation of different event operations
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
public enum OperationType {
    @JsonProperty("d") DELETE,
    @JsonProperty("u") UPDATE,
    @JsonProperty("c") CREATE
}
