package com.nsoft.chiwava.debezium.commons;

import com.nsoft.chiwava.debezium.commons.exception.DebeziumEventProcessorNotFoundException;

public interface ProcessorLocator {
    DebeziumEventProcessor locate(DebeziumEvent event)
            throws DebeziumEventProcessorNotFoundException;
}
