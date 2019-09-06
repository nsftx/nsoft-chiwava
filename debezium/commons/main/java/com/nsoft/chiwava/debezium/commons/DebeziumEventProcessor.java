package com.nsoft.chiwava.debezium.commons;

public interface DebeziumEventProcessor {
    void process(DebeziumEvent event);

    Boolean isApplicable(Candidate candidate);
}
