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

package com.nsoft.chiwava.core.commons.time;

import java.time.format.DateTimeFormatter;

/**
 * Convenience class to store some common date formats
 *
 * @author Mislav Milicevic
 * @since 2019-10-04
 */
public final class DateTimeFormats {

    public static final DateTimeFormatter UTC_DEFAULT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

    public static final DateTimeFormatter UTC_ZULU = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

    private DateTimeFormats() {
    }

}
