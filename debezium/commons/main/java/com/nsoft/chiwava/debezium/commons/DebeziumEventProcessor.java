package com.nsoft.chiwava.debezium.commons;

/**
 * Debezium event processor interface
 *
 * Implementors should be able to successfully process an incoming Debezium event
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
public interface DebeziumEventProcessor {
    /**
     * Should contain processing logic for incoming Debezium events
     *
     * @param event debezium event that needs to be processed
     */
    void process(DebeziumEvent event);

    /**
     * Should contain checking logic if the event processor is applicable for the passed candidate
     *
     * @param candidate candidate to check if the processor is applicable for it
     * @return if the candidate is valid
     */
    Boolean isApplicable(Candidate candidate);
}
