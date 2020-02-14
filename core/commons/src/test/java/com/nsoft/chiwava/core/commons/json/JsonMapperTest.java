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

import static com.nsoft.chiwava.support.DefaultJsonSerializationModel.MODEL_INSTANCE;
import static com.nsoft.chiwava.support.DefaultJsonSerializationModel.MODEL_STRING_COMPRESSED;
import static com.nsoft.chiwava.support.DefaultJsonSerializationModel.MODEL_STRING_PRETTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nsoft.chiwava.support.JsonSerializationModel;
import org.junit.jupiter.api.Test;

final class JsonMapperTest {

    @Test
    void toJson() {
        final JsonMapper mapper = new JsonMapper();

        final String jsonCompressed = mapper.toJson(MODEL_INSTANCE);
        assertEquals(MODEL_STRING_COMPRESSED, jsonCompressed);

        final String jsonPretty = mapper.toJson(MODEL_INSTANCE, true);
        assertEquals(MODEL_STRING_PRETTY, jsonPretty);
    }

    @Test
    void fromJson() {
        final JsonMapper mapper = new JsonMapper();

        final JsonSerializationModel modelFromCompressed = mapper.fromJson(MODEL_STRING_COMPRESSED,
                JsonSerializationModel.class);

        assertJsonModelEquals(MODEL_INSTANCE, modelFromCompressed);

        final JsonSerializationModel modelFromPretty = mapper.fromJson(MODEL_STRING_PRETTY,
                JsonSerializationModel.class);

        assertJsonModelEquals(MODEL_INSTANCE, modelFromPretty);
    }

    @Test
    void complex() {
        final JsonMapper mapper = new JsonMapper();

        assertNotNull(mapper.complex());
    }

    private void assertJsonModelEquals(final JsonSerializationModel expected,
            final JsonSerializationModel actual) {
        assertEquals(expected.getShortValue(), actual.getShortValue());
        assertEquals(expected.getIntegerValue(), actual.getIntegerValue());
        assertEquals(expected.getLongValue(), actual.getLongValue());
        assertEquals(expected.getFloatValue(), actual.getFloatValue());
        assertEquals(expected.getDoubleValue(), actual.getDoubleValue());
        assertEquals(expected.getSetValue(), actual.getSetValue());
        assertEquals(expected.getMapValue(), actual.getMapValue());
    }
}
