package com.nsoft.chiwava.core.commons.unit.regex;

import com.nsoft.chiwava.core.commons.regex.Patterns;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class PatternsTest {

    @Test
    public void shouldMatchUUID() {
        Assert.assertTrue(Patterns.isUUID(UUID.randomUUID().toString()));
    }

    @Test
    public void shouldNotMatchUUID() {
        Assert.assertFalse(Patterns.isUUID("Random blob of text"));
    }

    @Test
    public void shouldMatchEmail() {
        final String email = "example@example.com";

        Assert.assertTrue(Patterns.isEmail(email));
    }

    @Test
    public void shouldNotMatchEmail() {
        Assert.assertFalse(Patterns.isEmail("Random blob of text"));
    }
}
