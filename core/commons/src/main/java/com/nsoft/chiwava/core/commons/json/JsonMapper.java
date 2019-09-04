package com.nsoft.chiwava.core.commons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple wrapper of Jackson's Object Mapper
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

        /**
         * Adds a module to the registration list
         */
        public Builder withModule(Module module) {
            modules.add(module);
            return this;
        }

        /**
         * Adds multiple modules to the registration list
         * */
        public Builder withModules(Module... moduleList) {
            modules.addAll(Arrays.asList(moduleList));
            return this;
        }

        /**
         * Builds the JsonMapper with the previously configured options
         * */
        public JsonMapper build() {
            ObjectMapper objectMapper = new ObjectMapper();
            modules.forEach(objectMapper::registerModule);
            return new JsonMapper(objectMapper);
        }
    }

}
