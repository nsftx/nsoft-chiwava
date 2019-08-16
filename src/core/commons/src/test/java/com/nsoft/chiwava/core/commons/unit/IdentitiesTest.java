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
    public void shouldBeUniqueId() {
        Assert.assertTrue(Identities.isUUID(Identities.generateUniqueId()));
    }

}
