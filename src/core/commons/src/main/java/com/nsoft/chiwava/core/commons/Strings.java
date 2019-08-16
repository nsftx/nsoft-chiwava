package com.nsoft.chiwava.core.commons;

import java.util.Objects;

/**
 * Utility class used for various {@link String} checks and manipulations
 *
 * @author Mislav Milicevic
 * @since 2019-08-16
 */
public final class Strings {

    private Strings() {
    }

    /**
     * Capitalizes the first character of the input {@link String}
     *
     * @param input String to be capitalized
     * @return input with first character capitalized
     */
    public static String upperCaseFirst(String input) {
        return upperCaseFirst(input, 1);
    }

    /**
     * Capitalizes the first {@code n} characters of the input {@link String}
     *
     * @param input String to be capitalized
     * @param n number of characters to be capitalized
     * @return input with {@code n} first characters capitalized
     */
    public static String upperCaseFirst(String input, int n) {
        Objects.requireNonNull(input, "Input can't be null");

        if (n < 1 || n > input.length()) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 1 && %d <=}", n,
                            input.length()));
        }

        return input.substring(0, n).toUpperCase() + input.substring(n);
    }

    /**
     * Capitalizes the last character of the input {@link String}
     *
     * @param input String to be capitalized
     * @return input with the last character capitalized
     */
    public static String upperCaseLast(String input) {
        return upperCaseLast(input, 1);
    }

    /**
     * Capitalizes the last {@code n} characters of the input {@link String}
     *
     * @param input String to be capitalized
     * @param n number of characters to be capitalized
     * @return input with {@code n} last characters capitalized
     */
    public static String upperCaseLast(String input, int n) {
        Objects.requireNonNull(input, "Input can't be null");

        if (n < 1 || n > input.length()) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 1 && %d <=}", n,
                            input.length()));
        }

        int substringIndex = input.length() - n;
        return input.substring(0, substringIndex) + input.substring(substringIndex).toUpperCase();
    }

    /**
     * Converts the first character of the input {@link String} to lowercase
     *
     * @param input String to be converted to lowercase
     * @return input with first character converted to lowercase
     */
    public static String lowerCaseFirst(String input) {
        return lowerCaseFirst(input, 1);
    }

    /**
     * Converts the first {@code n} characters of the input {@link String} to lowercase
     *
     * @param input String to be converted to lowercase
     * @param n number of characters to be converted to lowercase
     * @return input with first {@code n} characters converted to lowercase
     */
    public static String lowerCaseFirst(String input, int n) {
        Objects.requireNonNull(input, "Input can't be null");

        if (n < 1 || n > input.length()) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 1 && %d <=}", n,
                            input.length()));
        }

        return input.substring(0, n).toLowerCase() + input.substring(n);
    }

    /**
     * Converts the last character of the input {@link String} to lowercase
     *
     * @param input String to be converted to lowercase
     * @return input with last character converted to lowercase
     */
    public static String lowerCaseLast(String input) {
        return lowerCaseLast(input, 1);
    }

    /**
     * Converts the last {@code n} characters of the input {@link String} to lowercase
     *
     * @param input String to be converted to lowercase
     * @param n number of characters to be converted to lowercase
     * @return input with last {@code n} characters converted to lowercase
     */
    public static String lowerCaseLast(String input, int n) {
        Objects.requireNonNull(input, "Input can't be null");

        if (n < 1 || n > input.length()) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 1 && %d <=}", n,
                            input.length()));
        }

        int substringIndex = input.length() - n;
        return input.substring(0, substringIndex) + input.substring(substringIndex).toLowerCase();
    }

    /**
     * Inserts a character into a {@link String} at a specified position
     *
     * @param input String to be inserted into
     * @param c character to be inserted
     * @param i index at which the character will be inserted
     * @return input with inserted character at specified index
     */
    public static String insertCharacterAt(String input, char c, int i) {
        Objects.requireNonNull(input, "Input can't be null");

        if (i < 0 || i > input.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 0 && %d <=}", i,
                            input.length() - 1));
        }

        return input.substring(0, i) + c + input.substring(i);
    }

    /**
     * Inserts a {@link String} into a {@link String} at a specified position
     *
     * @param input String to be inserted into
     * @param insert String to be inserted
     * @param i index at which the String will be inserted
     * @return input with inserted String at specified index
     */
    public static String insertStringAt(String input, String insert, int i) {
        Objects.requireNonNull(input, "Input can't be null");
        Objects.requireNonNull(insert, "Insert can't be null");

        if (i < 0 || i > input.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid character index {found: %d, expected: >= 0 && %d <=}", i,
                            input.length() - 1));
        }

        return input.substring(0, i) + insert + input.substring(i);
    }

    /**
     * Reverses the character order of the input {@link String}
     *
     * @param input String to be reversed
     * @return reversed input
     */
    public static String reverse(String input) {
        return new StringBuilder(Objects.requireNonNull(input, "Input can't be null")).reverse()
                .toString();
    }

}
