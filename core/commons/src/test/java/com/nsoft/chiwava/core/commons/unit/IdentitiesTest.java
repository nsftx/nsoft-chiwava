package com.nsoft.chiwava.core.commons.unit;

import com.nsoft.chiwava.core.commons.Identities;
import org.junit.Assert;
import org.junit.Test;

public class IdentitiesTest {

    @Test
    public void shouldGenerateUniqueId() {
        String first = Identities.generateUniqueId();
        String second = Identities.generateUniqueId();

        Assert.assertNotEquals(first, second);
    }

    @Test
    public void shouldGenerateStrippedUniqueId() {
        String first = Identities.generateUniqueIdStripped();
        String second = Identities.generateUniqueIdStripped();

        Assert.assertNotEquals(first, second);
    }

    @Test
    public void shouldReconstructStrippedUniqueId() {
        String validUUID = Identities.generateUniqueId();
        String reconstructedUUID = Identities.reconstructStrippedUUID(validUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        String strippedUUID = Identities.generateUniqueIdStripped();
        reconstructedUUID = Identities.reconstructStrippedUUID(strippedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructStrippedUniqueId_InvalidLength() {
        Identities.reconstructStrippedUUID("Invalid input");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructStrippedUniqueId_InvalidCharacter() {
        Identities.reconstructStrippedUUID("8a9574606dea4c39a732e48d05.26a05");
    }

    @Test
    public void shouldReconstructDamagedUniqueId() {
        String validUUID = "b6911c1a-7369-4a66-9e9e-b880ae6be365";
        String reconstructedUUID = Identities.reconstructDamagedUUID(validUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        String damagedUUID = "b6911c1a7369-4a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a73694a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a66-9e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-73694a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-7369-4a669e9e-b880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));

        damagedUUID = "b6911c1a-7369-4a669e9eb880ae6be365";
        reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructDamagedUUID_InvalidLength() {
        String damagedUUID = "b6911c1a73694a669e9b880ae6be365";
        String reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructDamagedUUID_InvalidCharacter() {
        String damagedUUID = "b6911c1a73694a669e9b880ae6b.e365";
        String reconstructedUUID = Identities.reconstructDamagedUUID(damagedUUID);
        Assert.assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test
    public void shouldBeUniqueId() {
        Assert.assertTrue(Identities.isUUID(Identities.generateUniqueId()));
    }

    @Test
    public void shouldNotBeUniqueId() {
        Assert.assertFalse(Identities.isUUID("Invalid UUID"));
    }

}
