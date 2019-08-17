package com.nsoft.chiwava.core.commons;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.function.IntUnaryOperator;
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

        final String first = input.substring(0, 8);
        final String second = input.substring(8, 12);
        final String third = input.substring(12, 16);
        final String fourth = input.substring(16, 20);
        final String fifth = input.substring(20);

        return first + '-' + second + '-' + third + '-' + fourth + '-' + fifth;
    }

    /**
     * Reconstructs a {@link UUID} with missing hyphens to a valid form.
     *
     * If the input is already a valid UUID, the input will be returned.
     *
     * <b>Note: The reconstruction process is quite expensive</b>
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

        final int[] originalHyphenIndices = {8, 13, 18, 23};

        int[] hyphenIndices = originalHyphenIndices.clone();

        for (int i = 0; i < hyphenIndices.length; i++) {
            if (input.charAt(hyphenIndices[i]) != '-') {
                hyphenIndices[i] = -1;
                for (int j = i + 1; j < hyphenIndices.length; j++) {
                    hyphenIndices[j]--;
                }
            }
        }

        int[] substringIndices = Arrays.stream(originalHyphenIndices).map(x -> x + 1).toArray();

        for (int i = 0; i < substringIndices.length; i++) {
            if (hyphenIndices[i] != -1) {
                substringIndices[i] = hyphenIndices[i];
                continue;
            } else {
                substringIndices[i] = originalHyphenIndices[i];
            }

            for (int j = i - 1; j >= 0; j--) {
                if (hyphenIndices[j] != -1) {
                    break;
                }

                substringIndices[i]--;
            }
        }

        IntUnaryOperator normalizeIndex = x -> x == -1 ? 0 : 1;

        final String first = input.substring(0, substringIndices[0]);
        final String second = input
                .substring(substringIndices[0] + normalizeIndex.applyAsInt(hyphenIndices[0]),
                        substringIndices[1]);
        final String third = input
                .substring(substringIndices[1] + normalizeIndex.applyAsInt(hyphenIndices[1]),
                        substringIndices[2]);
        final String fourth = input
                .substring(substringIndices[2] + normalizeIndex.applyAsInt(hyphenIndices[2]),
                        substringIndices[3]);
        final String fifth = input
                .substring(substringIndices[3] + normalizeIndex.applyAsInt(hyphenIndices[3])
                );

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
