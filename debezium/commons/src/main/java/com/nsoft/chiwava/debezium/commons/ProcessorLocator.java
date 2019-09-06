package com.nsoft.chiwava.debezium.commons;

import com.nsoft.chiwava.debezium.commons.exception.DebeziumEventProcessorNotFoundException;

/**
 * Processor locator interface
 *
 * Implementors should be able to locate an event processor based on the incoming event
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
public interface ProcessorLocator {
    /**
     * Should contain logic for event processor discovery
     *
     * @param event incoming event
     * @return debezium event processor if found
     * @throws DebeziumEventProcessorNotFoundException thrown if processor is not found
     */
    DebeziumEventProcessor locate(DebeziumEvent event)
            throws DebeziumEventProcessorNotFoundException;
}
