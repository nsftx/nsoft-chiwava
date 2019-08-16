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
    public void shouldReconstructUniqueId() {
        String strippedUUID = Identities.generateUniqueIdStripped();

        String reconstructedUUID = Identities.reconstructPotentialUUID(strippedUUID);

        Assert.assertTrue(Identities.isUUID(reconstructedUUID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructUniqueId_InvalidLength() {
        Identities.reconstructPotentialUUID("Invalid input");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReconstructUniqueId_InvalidCharacter() {
        Identities.reconstructPotentialUUID("8a9574606dea4c39a732e48d05.26a05");
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
