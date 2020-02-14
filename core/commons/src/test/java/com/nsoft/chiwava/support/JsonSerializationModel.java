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

package com.nsoft.chiwava.support;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;

/**
 * @author Mislav Milicevic
 * @since 2020-02-12
 */
public final class JsonSerializationModel {

    private final Short shortValue;
    private final Integer integerValue;
    private final Long longValue;
    private final Float floatValue;
    private final Double doubleValue;

    private final Set<String> setValue;
    private final Map<String, String> mapValue;

    @JsonCreator
    public JsonSerializationModel(
            @JsonProperty("shortValue") final Short shortValue,
            @JsonProperty("integerValue") final Integer integerValue,
            @JsonProperty("longValue") final Long longValue,
            @JsonProperty("floatValue") final Float floatValue,
            @JsonProperty("doubleValue") final Double doubleValue,
            @JsonProperty("setValue") final Set<String> setValue,
            @JsonProperty("mapValue") final Map<String, String> mapValue) {
        this.shortValue = shortValue;
        this.integerValue = integerValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.setValue = setValue;
        this.mapValue = mapValue;
    }

    public Short getShortValue() {
        return shortValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public Set<String> getSetValue() {
        return setValue;
    }

    public Map<String, String> getMapValue() {
        return mapValue;
    }

    public static JsonSerializationModelBuilder builder() {
        return JsonSerializationModelBuilder.aJsonSerializationModel();
    }

    public static final class JsonSerializationModelBuilder {
        private Short shortValue;
        private Integer integerValue;
        private Long longValue;
        private Float floatValue;
        private Double doubleValue;
        private Set<String> setValue;
        private Map<String, String> mapValue;

        private JsonSerializationModelBuilder() {
        }

        protected static JsonSerializationModelBuilder aJsonSerializationModel() {
            return new JsonSerializationModelBuilder();
        }

        public JsonSerializationModelBuilder withShortValue(Short shortValue) {
            this.shortValue = shortValue;
            return this;
        }

        public JsonSerializationModelBuilder withIntegerValue(Integer integerValue) {
            this.integerValue = integerValue;
            return this;
        }

        public JsonSerializationModelBuilder withLongValue(Long longValue) {
            this.longValue = longValue;
            return this;
        }

        public JsonSerializationModelBuilder withFloatValue(Float floatValue) {
            this.floatValue = floatValue;
            return this;
        }

        public JsonSerializationModelBuilder withDoubleValue(Double doubleValue) {
            this.doubleValue = doubleValue;
            return this;
        }

        public JsonSerializationModelBuilder withSetValue(Set<String> setValue) {
            this.setValue = setValue;
            return this;
        }

        public JsonSerializationModelBuilder withMapValue(Map<String, String> mapValue) {
            this.mapValue = mapValue;
            return this;
        }

        public JsonSerializationModel build() {
            return new JsonSerializationModel(shortValue, integerValue, longValue, floatValue,
                    doubleValue, setValue, mapValue);
        }
    }
}
