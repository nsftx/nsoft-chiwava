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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nsoft.chiwava.core.commons.json.JsonMapper;
import com.nsoft.chiwava.core.commons.json.conversion.LocalDateTimeDeserializer;
import com.nsoft.chiwava.core.commons.json.conversion.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * POJO representation of a Debezium event
 *
 * @author Mislav Milicevic
 * @since 2019-06-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEvent {

    private static final JsonMapper JSON_MAPPER;

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        JSON_MAPPER = new JsonMapper.Builder().withModule(javaTimeModule).build();
    }

    @Setter
    @Getter
    private EventId eventId;

    @Getter
    private Map<String, Object> before;
    @Getter
    private Map<String, Object> after;
    @Getter
    private Source source;

    @JsonProperty("op")
    @Getter
    private OperationType operation;

    @JsonProperty("ts_ms")
    @Getter
    private Long timestampMilliseconds;

    @Getter
    @JsonIgnore
    private Candidate candidate;

    @JsonCreator
    public DebeziumEvent(@JsonProperty("before") Map<String, Object> before,
            @JsonProperty("after") Map<String, Object> after, @JsonProperty("source") Source source,
            @JsonProperty("op") OperationType operation,
            @JsonProperty("ts_ms") Long timestampMilliseconds) {
        this.before = before;
        this.after = after;
        this.source = source;
        this.operation = operation;
        this.timestampMilliseconds = timestampMilliseconds;

        this.candidate =
                new Candidate(this.source.getDb(), this.source.getTable(), this.getOperation());
    }

    /**
     * Constructs the after state of the event
     *
     * @param <T> event model
     * @param clazz model class
     * @return constructed after state
     */
    public <T> Optional<T> constructAfterTo(Class<T> clazz) {
        if (this.getAfter() == null) {
            return Optional.empty();
        }

        return Optional.of(JSON_MAPPER.fromJson(JSON_MAPPER.toJson(this.getAfter()), clazz));
    }

    /**
     * Constructs the before state of the event
     *
     * @param <T> event model
     * @param clazz model class
     * @return constructed before state
     */
    public <T> Optional<T> constructBeforeTo(Class<T> clazz) {
        if (this.getBefore() == null) {
            return Optional.empty();
        }

        return Optional.of(JSON_MAPPER.fromJson(JSON_MAPPER.toJson(this.getBefore()), clazz));
    }

    /**
     * Returns an item from the event after state based on its key
     *
     * @param key item key
     * @return item from after state
     */
    public Object getAfterItemByKey(String key) {
        return this.getAfter().get(key);
    }

    /**
     * Return an item from the event before state based on its key
     *
     * @param key item key
     * @return item from before state
     */
    public boolean hasAfterItemByKey(String key) {
        return this.getAfter().containsKey(key);
    }
}
