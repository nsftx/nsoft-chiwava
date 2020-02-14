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

package com.nsoft.chiwava.core.commons.regex;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Convenience class to store useful regex patterns
 *
 * @author Mislav Milicevic
 * @since 2019-10-04
 */
public class Patterns {

    public static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

    private Patterns() {
    }

    /**
     * Checks if the input {@link String} matches a predefined UUID pattern
     *
     * @param input String
     * @return if input matches the UUID pattern
     */
    public static boolean isUUID(String input) {
        return matchesPattern(UUID_PATTERN, input);
    }

    /**
     * Checks if the input {@link String} matches a predefined email pattern
     *
     * @param input String
     * @return if input matches the email pattern
     */
    public static boolean isEmail(String input) {
        return matchesPattern(EMAIL_PATTERN, input);
    }

    private static boolean matchesPattern(Pattern pattern, CharSequence input) {
        Objects.requireNonNull(pattern, "pattern can't be null");
        Objects.requireNonNull(input, "input can't be null");

        return pattern.matcher(input).matches();
    }
}
