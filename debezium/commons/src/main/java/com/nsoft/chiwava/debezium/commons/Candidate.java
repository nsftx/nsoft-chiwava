/*
 * Copyright 2019-2020 NSoft
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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a potential candidate for event consumption
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
@AllArgsConstructor
public class Candidate {
    @Getter
    private String database;
    @Getter
    private String table;
    @Getter
    private OperationType operation;
}
