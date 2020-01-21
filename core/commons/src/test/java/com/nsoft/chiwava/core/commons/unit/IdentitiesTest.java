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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsoft.chiwava.core.commons.Identities;
import org.junit.jupiter.api.Test;

public class IdentitiesTest {

    @Test
    public void shouldGenerateUniqueId() {
        String first = Identities.generateUniqueId();
        String second = Identities.generateUniqueId();

        assertNotEquals(first, second);
    }

    @Test
    public void shouldGenerateStrippedUniqueId() {
        String first = Identities.generateUniqueIdStripped();
        String second = Identities.generateUniqueIdStripped();

        assertNotEquals(first, second);
    }

    @Test
    public void shouldReconstructStrippedUniqueId() {
        String validUUID = Identities.generateUniqueId();
        String reconstructedUUID = Identities.reconstructStrippedUUID(validUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        String strippedUUID = Identities.generateUniqueIdStripped();
        reconstructedUUID = Identities.reconstructStrippedUUID(strippedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test
    public void shouldNotReconstructStrippedUniqueId_InvalidLength() {
        assertThrows(IllegalArgumentException.class,
                () -> Identities.reconstructStrippedUUID("Invalid input"));
    }

    @Test
    public void shouldNotReconstructStrippedUniqueId_InvalidCharacter() {
        assertThrows(IllegalArgumentException.class,
                () -> Identities.reconstructStrippedUUID("8a9574606dea4c39a732e48d05.26a05"));
    }

    @Test
    public void shouldReconstructDamagedUniqueId() {
        String validUUID = "b6911c1a-7369-4a66-9e9e-b880ae6be365";
        String reconstructedUUID = Identities.reconstructDamagedUUID(validUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        String damagedUUID = "b6911c1a7369-4a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-7369-4a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-7369-4a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test
    public void shouldNotReconstructDamagedUUID_InvalidLength() {
        String damagedUUID = "b6911c1a73694a669e9b880ae6be365";
        assertThrows(IllegalArgumentException.class,
                () -> Identities.reconstructDamagedUUID(damagedUUID));
    }

    @Test
    public void shouldNotReconstructDamagedUUID_InvalidCharacter() {
        String damagedUUID = "b6911c1a73694a669e9b880ae6b.e365";
        assertThrows(IllegalArgumentException.class,
                () -> Identities.reconstructDamagedUUID(damagedUUID));
    }

    @Test
    public void shouldBeUniqueId() {
        assertTrue(Identities.isUUID(Identities.generateUniqueId()));
    }

    @Test
    public void shouldNotBeUniqueId() {
        assertFalse(Identities.isUUID("Invalid UUID"));
    }

}
