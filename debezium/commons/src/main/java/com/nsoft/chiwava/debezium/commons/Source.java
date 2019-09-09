package com.nsoft.chiwava.debezium.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * POJO representation of a debezium source object
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    @Getter
    private String version;

    @Getter
    private String name;

    @JsonProperty("server_id")
    @Getter
    private Long serverId;

    @JsonProperty("ts_sec")
    @Getter
    private Long timestampSeconds;

    @Getter
    private String gtid;
    @Getter
    private String file;

    @JsonProperty("pos")
    @Getter
    private Long position;

    @Getter
    private Integer row;
    @Getter
    private Boolean snapshot;
    @Getter
    private Integer thread;
    @Getter
    private String db;
    @Getter
    private String table;

    @JsonCreator
    public Source(@JsonProperty("version") String version, @JsonProperty("name") String name,
            @JsonProperty("server_id") Long serverId, @JsonProperty("ts_sec") Long timestampSeconds,
            @JsonProperty("gtid") String gtid, @JsonProperty("file") String file,
            @JsonProperty("pos") Long position, @JsonProperty("row") Integer row,
            @JsonProperty("snapshot") Boolean snapshot, @JsonProperty("thread") Integer thread,
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