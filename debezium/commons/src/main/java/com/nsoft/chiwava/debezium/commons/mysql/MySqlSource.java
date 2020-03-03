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

package com.nsoft.chiwava.debezium.commons.mysql;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * POJO representation of a mysql debezium source object
 *
 * @author Mislav Milicevic
 * @author Ivan Vucina
 *
 * @since 2019-06-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MySqlSource {
    @Getter
    private final String version;

    @Getter
    private final String name;

    @JsonProperty("server_id")
    @Getter
    private final Long serverId;

    @JsonProperty("ts_sec")
    @Getter
    private final Long timestampSeconds;

    @Getter
    private final String gtid;
    @Getter
    private final String file;

    @JsonProperty("pos")
    @Getter
    private final Long position;

    @Getter
    private final Integer row;
    @Getter
    private final Object snapshot;
    @Getter
    private final Integer thread;
    @Getter
    private final String db;
    @Getter
    private final String table;

    @JsonCreator
    public MySqlSource(@JsonProperty("version") String version, @JsonProperty("name") String name,
            @JsonProperty("server_id") Long serverId, @JsonProperty("ts_sec") Long timestampSeconds,
            @JsonProperty("gtid") String gtid, @JsonProperty("file") String file,
            @JsonProperty("pos") Long position, @JsonProperty("row") Integer row,
            @JsonProperty("snapshot") Object snapshot, @JsonProperty("thread") Integer thread,
            @JsonProperty("db") String db, @JsonProperty("table") String table) {
        this.version = version;
        this.name = name;
        this.serverId = serverId;
        this.timestampSeconds = timestampSeconds;
        this.gtid = gtid;
        this.file = file;
        this.position = position;
        this.row = row;
        this.snapshot = snapshot;
        this.thread = thread;
        this.db = db;
        this.table = table;
    }
}
