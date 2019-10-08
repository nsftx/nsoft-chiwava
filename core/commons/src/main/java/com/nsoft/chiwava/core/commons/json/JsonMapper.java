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

package com.nsoft.chiwava.core.commons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple wrapper of Jackson's Object Mapper
 *
 * <pre>
 *     final JsonMapper jsonMapper = new JsonMapper();
 *     or
 *     final JsonMapper jsonMapper = new JsonMapper.Builder().withModule(...).build();
 *     ...
 *
 *     &#64;AllArgsConstructor
 *     &#64;Getter
 *     public class Point {
 *         private int x;
 *         private int y;
 *     }
 *
 *     String pointToJson = jsonMapper.toJson(new Point(1, 1));
 *
 *     Point jsonToPoint = jsonMapper.fromJson(pointToJson, Point.class)
 * </pre>
 *
 * @author Mislav Milicevic
 * @since 2019-09-04
 */
public final class JsonMapper {

    private final ObjectMapper objectMapper;

    public JsonMapper() {
        this.objectMapper = new ObjectMapper();
    }

    private JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Converts the input object into a JSON String
     *
     * @param object input object
     * @return object converted to JSON String
     */
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException("An error occurred during serialization", e);
        }
    }

    /**
     * Generates an object instance from input JSON String
     *
     * @param json input JSON String
     * @param clazz runtime class wrapper
     * @param <T> type to map
     * @return object instance generated from JSON String
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        T obj;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonMapperException("An error occurred during deserialization", e);
        }
        return obj;
    }

    /**
     * Generates an object instance from input JSON String
     *
     * @param json input JSON String
     * @param typeReference type reference
     * @param <T> type to map
     * @return object instance generated from JSON String
     */
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        T obj;
        try {
            obj = objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonMapperException("An error occurred during deserialization", e);
        }
        return obj;
    }

    /**
     * Internal Builder class used to configure the JsonMapper before usage
     *
     * @author Mislav Milicevic
     * @since 2019-09-04
     */
    public static class Builder {

        private final Set<Module> modules = new HashSet<>();
        private boolean caseInsensitiveEnums = false;

        /**
         * Adds a module to the registration list
         *
         * @param module {@link Module} to register
         * @return {@link Builder} instance
         */
        public Builder withModule(Module module) {
            modules.add(module);
            return this;
        }

        /**
         * Adds multiple modules to the registration list
         *
         * @param moduleList list of modules to register
         * @return {@link Builder} instance
         */
        public Builder withModules(Module... moduleList) {
            modules.addAll(Arrays.asList(moduleList));
            return this;
        }

        /**
         * Configures mapper to accept case insensitive enums
         *
         * @param value boolean value
         * @return {@link Builder} instance
         */
        public Builder allowCaseInsensitiveEnums(boolean value) {
            this.caseInsensitiveEnums = value;
            return this;
        }

        /**
         * Builds the JsonMapper with the previously configured options
         *
         * @return built {@link JsonMapper}
         */
        public JsonMapper build() {
            ObjectMapper objectMapper = new ObjectMapper();

            modules.forEach(objectMapper::registerModule);

            if (caseInsensitiveEnums) {
                objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
            }

            return new JsonMapper(objectMapper);
        }
    }

}
