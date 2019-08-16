package com.nsoft.chiwava.core.commons.unit;

import com.nsoft.chiwava.core.commons.Strings;
import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    public void shouldUpperCaseFirstCharacter() {
        String result = Strings.upperCaseFirst("input");

        Assert.assertEquals("Input", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotUpperCaseFirstCharacter_NullInput() {
        Strings.upperCaseFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpperCaseFirstCharacter_InvalidIndexLower() {
        Strings.upperCaseFirst("input", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpperCaseFirstCharacter_InvalidIndexHigher() {
        Strings.upperCaseFirst("input", "input".length() + 1);
    }

    @Test
    public void shouldUpperCaseFirstTwoCharacters() {
        String result = Strings.upperCaseFirst("input", 2);

        Assert.assertEquals("INput", result);
    }

    @Test
    public void shouldUpperCaseLastCharacter() {
        String result = Strings.upperCaseLast("input");

        Assert.assertEquals("inpuT", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotUpperCaseLastCharacter_NullInput() {
        Strings.upperCaseLast(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpperCaseLastCharacter_InvalidIndexLower() {
        Strings.upperCaseLast("input", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpperCaseLastCharacter_InvalidIndexUpper() {
        Strings.upperCaseLast("input", "input".length() + 1);
    }

    @Test
    public void shouldUpperCaseLastTwoCharacters() {
        String result = Strings.upperCaseLast("input", 2);

        Assert.assertEquals("inpUT", result);
    }

    @Test
    public void shouldLowerCaseFirstCharacter() {
        String result = Strings.lowerCaseFirst("INPUT");

        Assert.assertEquals("iNPUT", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotLowerCaseFirstCharacter_NullInput() {
        Strings.lowerCaseFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotLowerCaseFirstCharacter_InvalidIndexLower() {
        Strings.lowerCaseFirst("INPUT", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotLowerCaseFirstCharacter_InvalidIndexHigher() {
        Strings.lowerCaseFirst("INPUT", "INPUT".length() + 1);
    }

    @Test
    public void shouldLowerCaseFirstTwoCharacters() {
        String result = Strings.lowerCaseFirst("INPUT", 2);

        Assert.assertEquals("inPUT", result);
    }

    @Test
    public void shouldLowerCaseLastCharacter() {
        String result = Strings.lowerCaseLast("INPUT");

        Assert.assertEquals("INPUt", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotLowerCaseLastCharacter_NullInput() {
        Strings.lowerCaseLast(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotLowerCaseLastCharacter_InvalidIndexLower() {
        Strings.lowerCaseLast("INPUT", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotLowerCaseLastCharacter_InvalidIndexUpper() {
        Strings.lowerCaseLast("INPUT", "INPUT".length() + 1);
    }

    @Test
    public void shouldLowerCaseLastTwoCharacters() {
        String result = Strings.lowerCaseLast("INPUT", 2);

        Assert.assertEquals("INPut", result);
    }

    @Test
    public void shouldInsertCharacter() {
        String result = Strings.insertCharacterAt("input", 'm', 1);

        Assert.assertEquals("imnput", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotInsertCharacter_NullInput() {
        Strings.insertCharacterAt(null, 'm', 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertCharacter_InvalidIndexLower() {
        Strings.insertCharacterAt("input", 'm', -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertCharacter_InvalidIndexUpper() {
        Strings.insertCharacterAt("input", 'm', "input".length());
    }

    @Test
    public void shouldReverse() {
        String result = Strings.reverse("input");

        Assert.assertEquals("tupni", result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotReverse_NullInput() {
        Strings.reverse(null);
    }
}
