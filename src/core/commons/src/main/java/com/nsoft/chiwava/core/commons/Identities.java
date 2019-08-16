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
     * Checks if the given input is in a valid UUID form
     *
     * @param input String input to check if it's in UUID form
     * @return if the input is in UUID form
     */
    public static boolean isUUID(String input) {
        return UUID_PATTERN.matcher(input).matches();
    }
}
