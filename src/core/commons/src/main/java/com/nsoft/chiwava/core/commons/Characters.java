package com.nsoft.chiwava.core.commons;

/**
 * Utility class used for various character checks and manipulations
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
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}
