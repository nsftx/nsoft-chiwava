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
