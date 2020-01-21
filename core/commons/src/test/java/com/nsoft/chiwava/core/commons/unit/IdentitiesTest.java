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
