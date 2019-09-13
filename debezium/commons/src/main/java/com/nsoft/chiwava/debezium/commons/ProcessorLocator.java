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
