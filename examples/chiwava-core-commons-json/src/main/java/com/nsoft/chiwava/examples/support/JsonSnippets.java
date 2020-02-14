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

package com.nsoft.chiwava.examples.support;

import static java.util.Objects.requireNonNull;

public final class JsonSnippets {

    public static String getCompressedPoint3dSnippet(final Point3d point3d) {
        requireNonNull(point3d);

        return getCompressedPoint3dSnippet(point3d.getX(), point3d.getY(), point3d.getZ());
    }

    public static String getCompressedPoint3dSnippet(double x, double y, double z) {
        return String.format("{\"x\":%f,\"y\":%f,\"z\":%f}", x, y, z);
    }

    public static String getFormattedPoint3dSnippet(final Point3d point3d) {
        return getFormattedPoint3dSnippet(point3d.getX(), point3d.getY(), point3d.getZ());
    }

    public static String getFormattedPoint3dSnippet(double x, double y, double z) {
        return String.format("{%n"
                + "  \"x\" : %f,%n"
                + "  \"y\" : %f,%n"
                + "  \"z\" : %f%n"
                + "}", x, y, z);
    }

    private JsonSnippets() {
    }
}
