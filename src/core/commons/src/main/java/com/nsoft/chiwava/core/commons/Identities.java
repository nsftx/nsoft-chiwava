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
                    String.format("Invalid input length {found: %s, expected: >= 32 && 35 <=}",
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

        int firstHyphenIndex = 8;
        int secondHyphenIndex = 13;
        int thirdHyphenIndex = 18;
        int fourthHyphenIndex = 23;

        if (input.charAt(firstHyphenIndex) != '-') {
            firstHyphenIndex = -1;
            secondHyphenIndex--;
            thirdHyphenIndex--;
            fourthHyphenIndex--;
        }

        if (input.charAt(secondHyphenIndex) != '-') {
            secondHyphenIndex = -1;
            thirdHyphenIndex--;
            fourthHyphenIndex--;
        }

        if (input.charAt(thirdHyphenIndex) != '-') {
            thirdHyphenIndex = -1;
            fourthHyphenIndex--;
        }

        if (input.charAt(fourthHyphenIndex) != '-') {
            fourthHyphenIndex = -1;
        }

        int firstSubstringIndex = firstHyphenIndex == -1 ? 8 : firstHyphenIndex;
        int secondSubstringIndex =
                secondHyphenIndex == -1 ? firstHyphenIndex == -1 ? 12 : 13
                        : secondHyphenIndex;
        int thirdSubstringIndex =
                thirdHyphenIndex == -1 ? secondHyphenIndex == -1 ? firstHyphenIndex == -1
                        ? 16 : 17 : 18 : thirdHyphenIndex;
        int fourthSubstringIndex =
                fourthHyphenIndex == -1 ? thirdHyphenIndex == -1 ? secondHyphenIndex == -1
                        ? firstHyphenIndex == -1 ? 20 : 21 : 22 : 23 : fourthHyphenIndex;

        String first = input.substring(0, firstSubstringIndex);
        String second = input.substring(
                firstHyphenIndex == -1 ? firstSubstringIndex : firstSubstringIndex + 1,
                secondSubstringIndex);
        String third = input.substring(
                secondHyphenIndex == -1 ? secondSubstringIndex : secondSubstringIndex + 1,
                thirdSubstringIndex);
        String fourth = input.substring(
                thirdHyphenIndex == -1 ? thirdSubstringIndex : thirdSubstringIndex + 1,
                fourthSubstringIndex);
        String fifth = input.substring(fourthHyphenIndex == -1 ? fourthSubstringIndex
                : fourthSubstringIndex + 1);

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
