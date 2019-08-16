package com.nsoft.chiwava.core.commons;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Utility class used for generating unique identifier
 *
 * @author Mislav Milicevic
 * @since 2019-08-16
 */
public final class Identities {

    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    private Identities() {
    }

    /**
     * Generates a Universally unique identifier ({@link UUID}) in String form
     *
     * @return UUID in String form
     */
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a Dash-stripped Universally unique identifier ({@link UUID}) in String form
     *
     * @return Dash-stripped UUID in String form
     */
    public static String generateUniqueIdStripped() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * Reconstructs a {@link UUID} in String format if the input is alphanumeric and 32 characters
     * in length
     *
     * @param input alphanumeric string with 32 characters
     * @return reconstructed UUID
     */
    public static String reconstructPotentialUUID(String input) {
        if (isUUID(input)) {
            return input;
        }

        if (input.length() != 32) {
            throw new IllegalArgumentException(
                    String.format("Invalid input length {found: %s, expected: 32}",
                            input.length()));
        }

        for (char c : input.toCharArray()) {
            if (!isCharacterAlphanumeric(c)) {
                throw new IllegalArgumentException(
                        String.format("Illegal character {found: %s}", c));
            }
        }

        String first = input.substring(0, 8);
        String second = input.substring(8, 12);
        String third = input.substring(12, 16);
        String fourth = input.substring(16, 20);
        String fifth = input.substring(20);

        return first + '-' + second + '-' + third + '-' + fourth + '-' + fifth;
    }

    /**
     * Checks if the given input is in a valid UUID form
     *
     * @param input String input to check if it's in UUID form
     * @return if the input is in UUID form
     */
    public static boolean isUUID(String input) {
        return UUID_PATTERN.matcher(input).matches();
    }

    private static boolean isCharacterAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}
