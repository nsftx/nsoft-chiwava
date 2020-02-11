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

package com.nsoft.chiwava.core.commons.unit.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.MapperFeature;
import com.nsoft.chiwava.core.commons.json.JsonMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

final class JsonMapperTest {

    @Test
    void shouldSerializePOJO() {
        final JsonMapper mapper = new JsonMapper();

        String json = mapper.toJson(new POJO());

        assertEquals(POJO.PREDEFINED_JSON, json);
    }

    @Test
    void shouldDeserializePOJO() {
        final JsonMapper mapper = new JsonMapper();

        POJO pojo = mapper.fromJson(POJO.PREDEFINED_JSON, POJO.class);

        assertEquals(new POJO(), pojo);
    }

    @Test
    void shouldDeserializeWrongCasedEnum() {
        final JsonMapper mapper = new JsonMapper.Builder()
                .withFeature(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();

        EnumObject eo = mapper.fromJson("\"hey\"", EnumObject.class);

        assertEquals(EnumObject.HEY, eo);
    }

    @Test
    void complex() {
        final JsonMapper mapper = new JsonMapper();

        assertNotNull(mapper.complex());
    }

    @Getter
    @Setter
    private static class POJO {
        public static final String PREDEFINED_JSON = "{\"value1\":\"1\",\"value2\":2,\"value3\":3,\"value4\":4.0,\"value5\":5.0}";

        private String value1 = "1";
        private int value2 = 2;
        private long value3 = 3L;
        private float value4 = 4f;
        private double value5 = 5d;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            POJO pojo = (POJO) o;

            if (value2 != pojo.value2) return false;
            if (value3 != pojo.value3) return false;
            if (Float.compare(pojo.value4, value4) != 0) return false;
            if (Double.compare(pojo.value5, value5) != 0) return false;
            return value1.equals(pojo.value1);
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = value1.hashCode();
            result = 31 * result + value2;
            result = 31 * result + (int) (value3 ^ (value3 >>> 32));
            result = 31 * result + (value4 != +0.0f ? Float.floatToIntBits(value4) : 0);
            temp = Double.doubleToLongBits(value5);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

    private enum EnumObject {
        HEY,
        THERE;
    }
}
