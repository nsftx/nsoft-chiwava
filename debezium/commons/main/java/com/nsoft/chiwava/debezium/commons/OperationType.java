package com.nsoft.chiwava.debezium.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperationType {
    @JsonProperty("d") DELETE,
    @JsonProperty("u") UPDATE,
    @JsonProperty("c") CREATE
}
