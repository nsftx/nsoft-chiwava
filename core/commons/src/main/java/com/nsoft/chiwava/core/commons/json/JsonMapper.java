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

package com.nsoft.chiwava.core.commons.json;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A wrapper for Jackson's Object Mapper meant to provide simple JSON serialization/deserialization
 * capabilities. If more advance features are required, invoking {@link #complex()} will return a
 * raw Jackson {@link ObjectMapper} with full functionality.
 * <p>
 * Examples:
 * <pre>
 *     final JsonMapper jsonMapper = new JsonMapper();
 *     or
 *     final JsonMapper jsonMapper = new JsonMapper.Builder().withModule(...).build();
 *     ...
 *
 *     &#64;AllArgsConstructor
 *     &#64;Getter
 *     public class Point {
 *         private final int x;
 *         private final int y;
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
     * Converts an input object into a JSON String and returns the result.
     *
     * @param object input object to serialize to a JSON String
     * @return a JSON String representation of the input object
     */
    public String toJson(Object object) {
        return toJson0(object, false);
    }

    /**
     * Converts an input object into a JSON String and returns the result.
     *
     * @param object input object to serialize to a JSON String
     * @param pretty if {@code true} a formatted JSON String will be returned, otherwise the String
     * will be compressed
     * @return a JSON String representation of the input object
     */
    public String toJson(Object object, boolean pretty) {
        return toJson0(object, pretty);
    }

    private String toJson0(Object object, boolean pretty) {
        try {
            return pretty
                    ? objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object)
                    : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException("An error occurred during serialization", e);
        }
    }

    /**
     * Converts a JSON String into a Java Object if the JSON String can be mapped to the provided
     * {@link Class}. Otherwise a {@link com.fasterxml.jackson.databind.JsonMappingException} is
     * thrown
     *
     * @param json input JSON String to convert
     * @param clazz class used as a mapping reference
     * @param <T> type to map
     * @return object instance mapped from a JSON String
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        requireNonNull(json, "json input can't be null");

        return fromJson0(json, clazz);
    }

    /**
     * Converts a JSON String into a Java Object if the JSON String can be mapped to the provided
     * {@link TypeReference}. Otherwise a {@link com.fasterxml.jackson.databind.JsonMappingException}
     * is thrown
     *
     * @param json input JSON String to convert
     * @param typeReference type used as a mapping reference
     * @param <T> type to map
     * @return object instance mapped from a JSON String
     */
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        requireNonNull(json, "json input can't be null");

        return fromJson0(json, typeReference);
    }

    private <T> T fromJson0(String json, Class<T> clazz) {
        T obj;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonMapperException("An error occurred during deserialization", e);
        }
        return obj;
    }

    private <T> T fromJson0(String json, TypeReference<T> typeReference) {
        T obj;
        try {
            obj = objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonMapperException("An error occurred during deserialization", e);
        }
        return obj;
    }

    /**
     * Returns the raw Jackson {@link ObjectMapper} instance
     *
     * @return raw Jackson {@link ObjectMapper} instance
     */
    public ObjectMapper complex() {
        return objectMapper;
    }

    /**
     * Internal {@link Builder} class used to configure the {@link JsonMapper} instance
     *
     * @author Mislav Milicevic
     * @since 2019-09-04
     */
    public static class Builder {

        private final Set<Module> modules = new HashSet<>();

        private final Map<MapperFeature, Boolean> mapperFeatures = new EnumMap<>(
                MapperFeature.class);
        private final Map<SerializationFeature, Boolean> serializationFeatures = new EnumMap<>(
                SerializationFeature.class);
        private final Map<DeserializationFeature, Boolean> deserializationFeatures = new EnumMap<>(
                DeserializationFeature.class);

        /**
         * Marks a module to be registered and returns the current {@link Builder} instance.
         *
         * @param module {@link Module} to register
         * @return current {@link Builder} instance
         */
        public Builder withModule(Module module) {
            modules.add(module);
            return this;
        }

        /**
         * Marks multiple modules to be registered and returns the current {@link Builder
         * instance}.
         *
         * @param moduleList list of modules to register
         * @return current {@link Builder} instance
         */
        public Builder withModules(Module... moduleList) {
            modules.addAll(Arrays.asList(moduleList));
            return this;
        }

        /**
         * Marks a feature to be enabled and returns the current {@link Builder} instance.
         *
         * @param feature {@link ConfigFeature} to enable
         * @return current {@link Builder} instance
         */
        public Builder withFeature(ConfigFeature feature) {
            enableFeature(feature);
            return this;
        }

        /**
         * Marks multiple features to be enabled and returns the current {@link Builder} instance.
         *
         * @param features {@link ConfigFeature} to enable
         * @return current {@link Builder} instance
         */
        public Builder withFeatures(ConfigFeature... features) {
            Arrays.asList(features).forEach(this::enableFeature);
            return this;
        }

        /**
         * Marks a feature to be disabled and returns the current {@link Builder} instance.
         *
         * @param feature {@link ConfigFeature} to enable
         * @return current {@link Builder} instance
         */
        public Builder withoutFeature(ConfigFeature feature) {
            disableFeature(feature);
            return this;
        }

        /**
         * Marks multiple features to be disabled and returns the current {@link Builder} instance.
         *
         * @param features {@link ConfigFeature} to enable
         * @return current {@link Builder} instance
         */
        public Builder withoutFeatures(ConfigFeature... features) {
            Arrays.asList(features).forEach(this::disableFeature);
            return this;
        }

        /**
         * Configures mapper to accept case insensitive enums
         *
         * @param value boolean value
         * @return {@link Builder} instance
         * @deprecated use {@link #withFeature} to enable the feature
         */
        @Deprecated
        public Builder allowCaseInsensitiveEnums(boolean value) {
            return value ? withFeature(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                    : withoutFeature(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        }

        /**
         * Builds the JsonMapper with the previously configured options
         *
         * @return built {@link JsonMapper}
         */
        public JsonMapper build() {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.registerModules(modules);

            mapperFeatures.forEach(objectMapper::configure);
            serializationFeatures.forEach(objectMapper::configure);
            deserializationFeatures.forEach(objectMapper::configure);

            return new JsonMapper(objectMapper);
        }

        private void enableFeature(ConfigFeature feature) {
            enableFeature(feature, true);
        }

        private void disableFeature(ConfigFeature feature) {
            enableFeature(feature, false);
        }

        private void enableFeature(ConfigFeature feature, boolean enable) {
            if (feature instanceof MapperFeature) {
                mapperFeatures.put((MapperFeature) feature, enable);
            } else if (feature instanceof SerializationFeature) {
                serializationFeatures.put((SerializationFeature) feature, enable);
            } else if (feature instanceof DeserializationFeature) {
                deserializationFeatures.put((DeserializationFeature) feature, enable);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
