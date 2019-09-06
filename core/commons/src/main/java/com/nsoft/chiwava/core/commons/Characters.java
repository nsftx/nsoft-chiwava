package com.nsoft.chiwava.core.commons;

/**
 * Utility class used for various character checks and manipulations
 *
 * Methods defined in this class are applicable to the ISO-8859-1 character set, characters outside
 * of its specification are not guaranteed to behave as expected
 *
 * @author Mislav Milicevic
 * @since 2019-08-16
 */
public final class Characters {

    private Characters() {
    }

    /**
     * Checks if the input character is alphanumeric
     *
     * @param c input character
     * @return if the character is alphanumeric
     */
    public static boolean isAlphanumeric(char c) {
        return isLetter(c) || isDigit(c);
    }

    /**
     * Checks if the input character is a letter
     *
     * @param c input character
     * @return if the number is a letter
     */
    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Checks if the input character is a digit
     *
     * @param c input character
     * @return if the character is a digit
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
