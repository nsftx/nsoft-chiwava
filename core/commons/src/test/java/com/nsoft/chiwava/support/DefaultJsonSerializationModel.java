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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DefaultJsonSerializationModel {

    public static final JsonSerializationModel MODEL_INSTANCE;
    public static final String MODEL_STRING_COMPRESSED = "{\"shortValue\":1,\"integerValue\":10,\"longValue\":100,\"floatValue\":3.14,\"doubleValue\":0.52958201,\"setValue\":[\"r57reGLIrb\",\"i2JRijjU6n\",\"tU8XIN4rto\",\"WRMqEGgNki\",\"CNX05MUxmo\",\"KOCbiP9YuW\"],\"mapValue\":{\"ONMBDUOPSS\":\"wVvVKSmqlD\",\"Zi1ekLExEo\":\"lO29Namx4x\",\"zca3cTBkNx\":\"50DHv155Jx\",\"ZErTIZ4NNd\":\"KXBGGbEgNu\",\"bhQv45wjUp\":\"hCUwO3cvjW\",\"LaD5nQqFK8\":\"Szy5xbfaYY\"}}";
    public static final String MODEL_STRING_PRETTY = "{\n"
            + "  \"shortValue\" : 1,\n"
            + "  \"integerValue\" : 10,\n"
            + "  \"longValue\" : 100,\n"
            + "  \"floatValue\" : 3.14,\n"
            + "  \"doubleValue\" : 0.52958201,\n"
            + "  \"setValue\" : [ \"r57reGLIrb\", \"i2JRijjU6n\", \"tU8XIN4rto\", \"WRMqEGgNki\", \"CNX05MUxmo\", \"KOCbiP9YuW\" ],\n"
            + "  \"mapValue\" : {\n"
            + "    \"ONMBDUOPSS\" : \"wVvVKSmqlD\",\n"
            + "    \"Zi1ekLExEo\" : \"lO29Namx4x\",\n"
            + "    \"zca3cTBkNx\" : \"50DHv155Jx\",\n"
            + "    \"ZErTIZ4NNd\" : \"KXBGGbEgNu\",\n"
            + "    \"bhQv45wjUp\" : \"hCUwO3cvjW\",\n"
            + "    \"LaD5nQqFK8\" : \"Szy5xbfaYY\"\n"
            + "  }\n"
            + "}";

    static {
        final Set<String> set = new HashSet<>();
        set.add("KOCbiP9YuW");
        set.add("i2JRijjU6n");
        set.add("r57reGLIrb");
        set.add("CNX05MUxmo");
        set.add("tU8XIN4rto");
        set.add("WRMqEGgNki");

        final Map<String, String> map = new HashMap<>();
        map.put("ZErTIZ4NNd", "KXBGGbEgNu");
        map.put("zca3cTBkNx", "50DHv155Jx");
        map.put("Zi1ekLExEo", "lO29Namx4x");
        map.put("ONMBDUOPSS", "wVvVKSmqlD");
        map.put("LaD5nQqFK8", "Szy5xbfaYY");
        map.put("bhQv45wjUp", "hCUwO3cvjW");

        MODEL_INSTANCE = JsonSerializationModel.builder()
                .withShortValue((short) 1)
                .withIntegerValue(10)
                .withLongValue(100L)
                .withFloatValue(3.14f)
                .withDoubleValue(0.52958201)
                .withSetValue(set)
                .withMapValue(map)
                .build();
    }

    private DefaultJsonSerializationModel() {
    }
}
