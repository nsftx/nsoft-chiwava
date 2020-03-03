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

package com.nsoft.chiwava.debezium.commons.postgres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * POJO representation of a postgres debezium source object
 *
 * @author Ivan Vucina
 *
 * @since 2020-03-03
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostgresSource {
    @Getter
    private final String version;

    @Getter
    private final String name;

    @JsonProperty("ts_ms")
    @Getter
    private final Long timestampMilliseconds;

    @Getter
    private final String snapshot;

    @JsonProperty("db")
    @Getter
    private final String database;

    @Getter
    private final String schema;

    @Getter
    private final String table;

    @Getter
    private final Long txId;

    @Getter
    private final Long lsn;

    @Getter
    private final Long xmin;

    @JsonCreator
    public PostgresSource(
            @JsonProperty("version") String version,
            @JsonProperty("name") String name,
            @JsonProperty("ts_ms") Long timestampMilliseconds,
            @JsonProperty("snapshot") String snapshot,
            @JsonProperty("db") String database,
            @JsonProperty("schema") String schema,
            @JsonProperty("table") String table,
            @JsonProperty("txId") Long txId,
            @JsonProperty("lsn") Long lsn,
            @JsonProperty("xmin") Long xmin) {
        this.version = version;
        this.name = name;
        this.timestampMilliseconds = timestampMilliseconds;
        this.snapshot = snapshot;
        this.database = database;
        this.schema = schema;
        this.table = table;
        this.txId = txId;
        this.lsn = lsn;
        this.xmin = xmin;
    }
}
