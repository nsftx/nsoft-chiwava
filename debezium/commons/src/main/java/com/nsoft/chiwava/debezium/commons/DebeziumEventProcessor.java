/*
 * Copyright 2019 NSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
