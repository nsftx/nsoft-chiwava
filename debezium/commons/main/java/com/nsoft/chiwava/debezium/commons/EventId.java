package com.nsoft.chiwava.debezium.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class EventId {
    @Getter
    private Object id;

    @JsonCreator
    public EventId(@JsonProperty("version_id") Object id) {
        this.id = id;
    }
}
