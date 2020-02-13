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
import com.nsoft.chiwava.examples.support.JsonSnippets;
import com.nsoft.chiwava.examples.support.Point3d;

public final class SimpleJsonToObjectDeserialization {

    public static void main(String[] args) {

        final JsonMapper jsonMapper = new JsonMapper();

        final Point3d referencePoint = new Point3d(1, 1, 1);

        // This String is predefined, no serialization is being done here
        final String jsonCompressed = JsonSnippets.getCompressedPoint3dSnippet(referencePoint);

        System.out.printf("Converting compressed JSON to %s%n",
                referencePoint.getClass().getSimpleName());
        System.out.printf("JSON = %s%n%n", jsonCompressed);

        // Converts a compressed JSON String into a raw Java object
        final Point3d pointFromCompressed = jsonMapper.fromJson(jsonCompressed, Point3d.class);

        System.out.printf("Converted %s%n", pointFromCompressed);

        System.out.println("-".repeat(80));

        // This String is predefined, no serialization is being done here
        final String jsonFormatted = JsonSnippets.getFormattedPoint3dSnippet(referencePoint);

        System.out.printf("Converting formatted JSON to %s%n",
                referencePoint.getClass().getSimpleName());
        System.out.printf("JSON = %s%n%n", jsonFormatted);

        // Converts a formatted JSON String into a raw Java object
        final Point3d pointFromFormatted = jsonMapper.fromJson(jsonFormatted, Point3d.class);

        System.out.printf("Converted %s%n", pointFromFormatted);

    }
}
