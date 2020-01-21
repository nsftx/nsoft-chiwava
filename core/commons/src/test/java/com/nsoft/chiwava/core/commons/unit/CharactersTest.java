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

package com.nsoft.chiwava.core.commons.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsoft.chiwava.core.commons.Characters;
import org.junit.jupiter.api.Test;

final class CharactersTest {

    @Test
    void shouldBeAlphanumeric() {
        for (char c = 'a'; c <= 'z'; c++) {
            assertTrue(Characters.isAlphanumeric(c));
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            assertTrue(Characters.isAlphanumeric(c));
        }

        for (char c = '0'; c <= '9'; c++) {
            assertTrue(Characters.isAlphanumeric(c));
        }
    }

    @Test
    public void shouldNotBeAlphanumeric() {
        assertFalse(Characters.isAlphanumeric('.'));
    }

    @Test
    public void shouldBeLetter() {
        for (char c = 'a'; c <= 'z'; c++) {
            assertTrue(Characters.isLetter(c));
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            assertTrue(Characters.isLetter(c));
        }
    }

    @Test
    public void shouldNotBeLetter() {
        assertFalse(Characters.isLetter('.'));
    }

    @Test
    public void shouldBeDigit() {
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(Characters.isDigit(c));
        }
    }

    @Test
    public void shouldNotBeDigit() {
        assertFalse(Characters.isDigit('.'));
    }
}
