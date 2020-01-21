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

package com.nsoft.chiwava.core.commons.unit.regex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsoft.chiwava.core.commons.regex.Patterns;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PatternsTest {

    @Test
    public void shouldMatchUUID() {
        assertTrue(Patterns.isUUID(UUID.randomUUID().toString()));
    }

    @Test
    public void shouldNotMatchUUID() {
        assertFalse(Patterns.isUUID("Random blob of text"));
    }

    @Test
    public void shouldMatchEmail() {
        final String email = "example@example.com";

        assertTrue(Patterns.isEmail(email));
    }

    @Test
    public void shouldNotMatchEmail() {
        assertFalse(Patterns.isEmail("Random blob of text"));
    }
}
