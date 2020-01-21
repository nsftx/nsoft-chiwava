package com.nsoft.chiwava.core.commons.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsoft.chiwava.core.commons.Strings;
import org.junit.jupiter.api.Test;

public class StringsTest {

    @Test
    public void shouldUpperCaseFirstCharacter() {
        String result = Strings.upperCaseFirst("input");

        assertEquals("Input", result);
    }

    @Test
    public void shouldNotUpperCaseFirstCharacter_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.upperCaseFirst(null));
    }

    @Test
    public void shouldNotUpperCaseFirstCharacter_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class, () -> Strings.upperCaseFirst("input", 0));
    }

    @Test
    public void shouldNotUpperCaseFirstCharacter_InvalidIndexHigher() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.upperCaseFirst("input", "input".length() + 1));
    }

    @Test
    public void shouldUpperCaseFirstTwoCharacters() {
        String result = Strings.upperCaseFirst("input", 2);

        assertEquals("INput", result);
    }

    @Test
    public void shouldUpperCaseLastCharacter() {
        String result = Strings.upperCaseLast("input");

        assertEquals("inpuT", result);
    }

    @Test
    public void shouldNotUpperCaseLastCharacter_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.upperCaseLast(null));
    }

    @Test
    public void shouldNotUpperCaseLastCharacter_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class, () -> Strings.upperCaseLast("input", 0));
    }

    @Test
    public void shouldNotUpperCaseLastCharacter_InvalidIndexUpper() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.upperCaseLast("input", "input".length() + 1));
    }

    @Test
    public void shouldUpperCaseLastTwoCharacters() {
        String result = Strings.upperCaseLast("input", 2);

        assertEquals("inpUT", result);
    }

    @Test
    public void shouldLowerCaseFirstCharacter() {
        String result = Strings.lowerCaseFirst("INPUT");

        assertEquals("iNPUT", result);
    }

    @Test
    public void shouldNotLowerCaseFirstCharacter_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.lowerCaseFirst(null));
    }

    @Test
    public void shouldNotLowerCaseFirstCharacter_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class, () -> Strings.lowerCaseFirst("INPUT", 0));
    }

    @Test
    public void shouldNotLowerCaseFirstCharacter_InvalidIndexHigher() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.lowerCaseFirst("INPUT", "INPUT".length() + 1));
    }

    @Test
    public void shouldLowerCaseFirstTwoCharacters() {
        String result = Strings.lowerCaseFirst("INPUT", 2);

        assertEquals("inPUT", result);
    }

    @Test
    public void shouldLowerCaseLastCharacter() {
        String result = Strings.lowerCaseLast("INPUT");

        assertEquals("INPUt", result);
    }

    @Test
    public void shouldNotLowerCaseLastCharacter_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.lowerCaseLast(null));
    }

    @Test
    public void shouldNotLowerCaseLastCharacter_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class, () -> Strings.lowerCaseLast("INPUT", 0));
    }

    @Test
    public void shouldNotLowerCaseLastCharacter_InvalidIndexUpper() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.lowerCaseLast("INPUT", "INPUT".length() + 1));
    }

    @Test
    public void shouldLowerCaseLastTwoCharacters() {
        String result = Strings.lowerCaseLast("INPUT", 2);

        assertEquals("INPut", result);
    }

    @Test
    public void shouldInsertCharacter() {
        String result = Strings.insertCharacterAt("input", 'm', 1);

        assertEquals("imnput", result);
    }

    @Test
    public void shouldNotInsertCharacter_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.insertCharacterAt(null, 'm', 1));
    }

    @Test
    public void shouldNotInsertCharacter_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.insertCharacterAt("input", 'm', -1));
    }

    @Test
    public void shouldNotInsertCharacter_InvalidIndexUpper() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.insertCharacterAt("input", 'm', "input".length()));
    }

    @Test
    public void shouldInsertString() {
        String result = Strings.insertStringAt("input", "put", 2);

        assertEquals("inputput", result);
    }

    @Test
    public void shouldNotInsertString_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.insertStringAt(null, "put", 2));
    }

    @Test
    public void shouldNotInsertString_NullInsert() {
        assertThrows(NullPointerException.class, () -> Strings.insertStringAt("input", null, 2));
    }

    @Test
    public void shouldNotInsertString_InvalidIndexLower() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.insertStringAt("input", "put", -1));
    }

    @Test
    public void shouldNotInsertString_InvalidIndexUpper() {
        assertThrows(IllegalArgumentException.class,
                () -> Strings.insertStringAt("input", "put", "input".length()));
    }

    @Test
    public void shouldReverse() {
        String result = Strings.reverse("input");

        assertEquals("tupni", result);
    }

    @Test
    public void shouldNotReverse_NullInput() {
        assertThrows(NullPointerException.class, () -> Strings.reverse(null));
    }

    @Test
    public void shouldGetNumberOfUpperCaseCharacters() {
        String input = "iNpuT";

        int num = Strings.getNumberOfUpperCaseCharacters(input);

        assertEquals(2, num);
    }

    @Test
    public void shouldNotGetNumberOfUpperCaseCharacters_NullInput() {
        assertThrows(NullPointerException.class,
                () -> Strings.getNumberOfUpperCaseCharacters(null));
    }

    @Test
    public void shouldGetNumberOfLowerCaseCharacters() {
        String input = "iNpuT";

        int num = Strings.getNumberOfLowerCaseCharacters(input);

        assertEquals(3, num);
    }

    @Test
    public void shouldNotGetNumberOfLowerCaseCharacters_NullInput() {
        assertThrows(NullPointerException.class,
                () -> Strings.getNumberOfLowerCaseCharacters(null));
    }

    @Test
    public void shouldBeAlphanumericOnly() {
        String input = "AbCdE123";
        assertTrue(Strings.isAlphanumericOnly(input));

        input = "AbCdE123.-";
        assertFalse(Strings.isAlphanumericOnly(input));
    }
}
