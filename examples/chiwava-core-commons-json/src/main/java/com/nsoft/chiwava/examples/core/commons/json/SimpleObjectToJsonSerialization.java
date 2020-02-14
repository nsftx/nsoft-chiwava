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

package com.nsoft.chiwava.examples.core.commons.json;

import com.nsoft.chiwava.core.commons.json.JsonMapper;
import com.nsoft.chiwava.examples.support.Point3d;

public final class SimpleObjectToJsonSerialization {

    public static void main(String[] args) {
        final JsonMapper jsonMapper = new JsonMapper();

        final Point3d point = new Point3d(1, 1, 1);

        // Converts a raw Java object into a compressed JSON String
        final String jsonCompressed = jsonMapper.toJson(point);

        System.out
                .printf("%s to compressed JSON conversion: %s%n", point.getClass().getSimpleName(),
                        jsonCompressed);

        System.out.println();

        // Converts a raw Java object into a formatted JSON String
        final String jsonPretty = jsonMapper.toJson(point, true);

        System.out
                .printf("%s to formatted JSON conversion: %n%s%n", point.getClass().getSimpleName(),
                        jsonPretty);
    }
}
