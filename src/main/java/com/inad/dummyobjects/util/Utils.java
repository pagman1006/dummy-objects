package com.inad.dummyobjects.util;

import com.inad.dummyobjects.Factory;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Provides small random-value helpers used by {@link Factory} to generate realistic dummy data.
 * <p>
 * The class centralizes a shared {@link Random} instance and exposes convenience methods for
 * strings, numeric ranges, booleans, and enum values. It is designed as a lightweight support
 * utility for unit tests and object-factory scenarios where repeatedly creating synthetic values
 * is more convenient than hand-writing fixtures.
 * </p>
 */
public class Utils {

    /**
     * The random number generator used for generating random values.
     */
    private static final Random random = new Random();

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
    }

    /**
     * Generates a random string of the specified length.
     *
     * @param length       The length of the string to generate.
     * @param characterSet The string containing the set of characters to choose from.
     * @return A random string constructed from characters in {@code characterSet}.
     */
    public static String randomString(final int length, final String characterSet) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            sb.append(characterSet.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Generates a random long value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random long between {@code min} and {@code max}.
     */
    public static Long randomNumber(final long min, final long max) {
        return random.nextLong(min, max);
    }

    /**
     * Generates a random float value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random float between {@code min} and {@code max}.
     */
    public static Float randomNumber(final float min, final float max) {
        return random.nextFloat(min, max);
    }

    /**
     * Generates a random double value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random double between {@code min} and {@code max}.
     */
    public static Double randomNumber(final double min, final double max) {
        return random.nextDouble(min, max);
    }

    /**
     * Generates a random boolean value.
     *
     * @return {@code true} or {@code false} randomly.
     */
    public static Boolean randomBoolean() {
        return random.nextBoolean();
    }


    /**
     * Selects a random constant from the supplied enum type.
     *
     * @param clazz The enum class to sample from.
     * @param <T> The enum type.
     * @return A randomly selected enum constant, or {@code null} when the enum has no constants.
     * @throws ClassCastException If the supplied class is not an enum.
     */
    public static <T> T randomEnum(final Class<T> clazz) {
        if (!clazz.isEnum()) {
            throw new ClassCastException("Class is not an enum.");
        }

        T[] enums = clazz.getEnumConstants();
        if (enums == null || enums.length == 0) {
            return null;
        }

        int rand = randomNumber(0, enums.length);
        return enums[rand];
    }

    /**
     * Generates a random integer value within the specified range.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random int between {@code min} and {@code max}.
     */
    public static Integer randomNumber(final int min, final int max) {
        return random.nextInt(min, max);
    }

}