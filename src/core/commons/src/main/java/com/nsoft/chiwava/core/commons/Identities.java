package com.nsoft.chiwava.core.commons;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Utility class used for various checks and manipulations of unique identifiers
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
     * in length.
     *
     * If the input is already a valid UUID, the input will be returned.
     *
     * @param input alphanumeric string with 32 characters
     * @return reconstructed UUID
     */
    public static String reconstructStrippedUUID(String input) {
        Objects.requireNonNull(input, "Input can't be null");

        if (isUUID(input)) {
            return input;
        }

        if (input.length() != 32) {
            throw new IllegalArgumentException(
                    String.format("Invalid input length {found: %s, expected: 32}",
                            input.length()));
        }

        for (char c : input.toCharArray()) {
            if (!Characters.isAlphanumeric(c)) {
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
     * Reconstructs a {@link UUID} with missing hyphens to a valid form.
     *
     * If the input is already a valid UUID, the input will be returned.
     *
     * @param input UUID with missing hyphens
     * @return reconstructed UUID
     */
    public static String reconstructDamagedUUID(String input) {
        Objects.requireNonNull(input, "Input can't be null");

        if (isUUID(input)) {
            return input;
        }

        if (input.length() < 32 || input.length() > 35) {
            throw new IllegalArgumentException(
                    String.format("Invalid input length {found: %s, expected: 32..35}",
                            input.length()));
        }

        int hyphenCount = 0;

        for (char c : input.toCharArray()) {
            if (!Characters.isAlphanumeric(c)) {
                if (c == '-') {
                    if (hyphenCount > 4) {
                        throw new IllegalArgumentException("Too many hyphens");
                    }
                    hyphenCount++;
                    continue;
                }
                throw new IllegalArgumentException(
                        String.format("Illegal character {found: %s}", c));
            }
        }

        int firstHyphenPosition = 8;
        int secondHyphenPosition = 13;
        int thirdHyphenPosition = 18;
        int fourthHyphenPosition = 23;

        if (input.charAt(firstHyphenPosition) != '-') {
            firstHyphenPosition = -1;
            secondHyphenPosition--;
            thirdHyphenPosition--;
            fourthHyphenPosition--;
        }

        if (input.charAt(secondHyphenPosition) != '-') {
            secondHyphenPosition = -1;
            thirdHyphenPosition--;
            fourthHyphenPosition--;
        }

        if (input.charAt(thirdHyphenPosition) != '-') {
            thirdHyphenPosition = -1;
            fourthHyphenPosition--;
        }

        if (input.charAt(fourthHyphenPosition) != '-') {
            fourthHyphenPosition = -1;
        }

        int firstSubstringPosition = firstHyphenPosition == -1 ? 8 : firstHyphenPosition;
        int secondSubstringPosition =
                secondHyphenPosition == -1 ? firstHyphenPosition == -1 ? 12 : 13
                        : secondHyphenPosition;
        int thirdSubstringPosition =
                thirdHyphenPosition == -1 ? secondHyphenPosition == -1 ? firstHyphenPosition == -1
                        ? 16 : 17 : 18 : thirdHyphenPosition;
        int fourthSubstringPosition =
                fourthHyphenPosition == -1 ? thirdHyphenPosition == -1 ? secondHyphenPosition == -1
                        ? firstHyphenPosition == -1 ? 20 : 21 : 22 : 23 : fourthHyphenPosition;

        String first = input.substring(0, firstSubstringPosition);
        String second = input.substring(
                firstHyphenPosition == -1 ? firstSubstringPosition : firstSubstringPosition + 1,
                secondSubstringPosition);
        String third = input.substring(
                secondHyphenPosition == -1 ? secondSubstringPosition : secondSubstringPosition + 1,
                thirdSubstringPosition);
        String fourth = input.substring(
                thirdHyphenPosition == -1 ? thirdSubstringPosition : thirdSubstringPosition + 1,
                fourthSubstringPosition);
        String fifth = input.substring(fourthHyphenPosition == -1 ? fourthSubstringPosition
                : fourthSubstringPosition + 1);

        return first + '-' + second + '-' + third + '-' + fourth + '-' + fifth;
    }

    /**
     * Checks if the given input is in a valid UUID form
     *
     * @param input String input to check if it's in UUID form
     * @return if the input is in UUID form
     */
    public static boolean isUUID(String input) {
        return UUID_PATTERN.matcher(Objects.requireNonNull(input, "Input can't be null")).matches();
    }

}
