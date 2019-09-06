package com.nsoft.chiwava.debezium.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * POJO representation of a Debezium event id
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
public class EventId {
    @Getter
    private Object id;

    @JsonCreator
    public EventId(@JsonProperty("version_id") Object id) {
        this.id = id;
    }
}
