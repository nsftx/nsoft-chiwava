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

package com.nsoft.chiwava.core.commons;

/**
 * Utility class used for various character checks and manipulations
 *
 * Methods defined in this class are applicable to the ISO-8859-1 character set, characters outside
 * of its specification are not guaranteed to behave as expected
 *
 * @author Mislav Milicevic
 * @since 2019-08-16
 */
public final class Characters {

    private Characters() {
    }

    /**
     * Checks if the input character is alphanumeric
     *
     * @param c input character
     * @return if the character is alphanumeric
     */
    public static boolean isAlphanumeric(char c) {
        return isLetter(c) || isDigit(c);
    }

    /**
     * Checks if the input character is a letter
     *
     * @param c input character
     * @return if the number is a letter
     */
    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Checks if the input character is a digit
     *
     * @param c input character
     * @return if the character is a digit
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
