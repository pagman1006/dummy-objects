package com.inad;

import java.lang.reflect.Field;
import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    public static String randomString(final int length, final String characterSet) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            sb.append(characterSet.charAt(index));
        }
        return sb.toString();
    }

    public static int randomNumber(final int min, final int max) {
        return random.nextInt(min, max);
    }

    public static long randomNumber(final long min, final long max) {
        return random.nextLong(min, max);
    }

    public static float randomNumber(final float min, final float max) {
        return random.nextFloat(min, max);
    }

    public static double randomNumber(final double min, final double max) {
        return random.nextDouble(min, max);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E randomEnum(final Field field) throws ClassNotFoundException, ClassCastException {
        E[] enums = (E[]) Class.forName(field.getType().getName()).getEnumConstants();
        int rand = randomNumber(0, enums.length);
        return enums[rand];
    }

}