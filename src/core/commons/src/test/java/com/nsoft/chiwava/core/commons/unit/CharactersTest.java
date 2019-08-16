package com.nsoft.chiwava.core.commons.unit;

import com.nsoft.chiwava.core.commons.Characters;
import org.junit.Assert;
import org.junit.Test;

public class CharactersTest {

    @Test
    public void shouldBeAlphanumeric() {
        for (char c = 'a'; c <= 'z'; c++) {
            Assert.assertTrue(Characters.isAlphanumeric(c));
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            Assert.assertTrue(Characters.isAlphanumeric(c));
        }

        for (char c = '0'; c <= '9'; c++) {
            Assert.assertTrue(Characters.isAlphanumeric(c));
        }
    }

    @Test
    public void shouldNotBeAlphanumeric() {
        Assert.assertFalse(Characters.isAlphanumeric('.'));
    }
}
