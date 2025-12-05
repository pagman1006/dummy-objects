package com.inad.dummyobjects.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    // --- Random String Tests ---

    @Test
    @DisplayName("randomString should return string of correct length and characters")
    void testRandomString() {
        String charset = "abc";
        int length = 10;
        String result = Utils.randomString(length, charset);

        assertEquals(length, result.length(), "The generated string length is incorrect");
        for (char c : result.toCharArray()) {
            assertTrue(charset.indexOf(c) >= 0, "Generated string contains invalid character: " + c);
        }
    }

    @Test
    @DisplayName("randomString with length 0 returns empty string")
    void testRandomStringEmpty() {
        String result = Utils.randomString(0, "abc");
        assertEquals("", result);
    }

    // --- Random Number Tests (Long) ---

    @RepeatedTest(5)
    @DisplayName("randomNumber(long) returns value within range")
    void testRandomNumberLong() {
        long min = 100L;
        long max = 200L;
        long result = Utils.randomNumber(min, max);

        assertTrue(result >= min && result < max,
                () -> "Value " + result + " is not in range [" + min + ", " + max + ")");
    }

    // --- Random Number Tests (Float) ---

    @RepeatedTest(5)
    @DisplayName("randomNumber(float) returns value within range")
    void testRandomNumberFloat() {
        float min = 1.5f;
        float max = 5.5f;
        float result = Utils.randomNumber(min, max);

        assertTrue(result >= min && result < max,
                () -> "Value " + result + " is not in range [" + min + ", " + max + ")");
    }

    // --- Random Number Tests (Double) ---

    @RepeatedTest(5)
    @DisplayName("randomNumber(double) returns value within range")
    void testRandomNumberDouble() {
        double min = 10.0;
        double max = 20.0;
        double result = Utils.randomNumber(min, max);

        assertTrue(result >= min && result < max,
                () -> "Value " + result + " is not in range [" + min + ", " + max + ")");
    }

    // --- Random Number Tests (Int) ---

    @RepeatedTest(5)
    @DisplayName("randomNumber(int) returns value within range")
    void testRandomNumberInt() {
        int min = 5;
        int max = 10;
        int result = Utils.randomNumber(min, max);

        assertTrue(result >= min && result < max,
                () -> "Value " + result + " is not in range [" + min + ", " + max + ")");
    }

    // --- Random Boolean Tests ---

    @Test
    @DisplayName("randomBoolean does not throw exception")
    void testRandomBoolean() {
        // Since it's random, we just ensure it returns a valid boolean value without crashing
        assertDoesNotThrow(Utils::randomBoolean);

    }

    // --- Random Enum Tests ---

    private enum DummyEnum {
        ONE, TWO, THREE
    }

    @SuppressWarnings("unused")
    private DummyEnum dummyField;

    @SuppressWarnings("unused")
    private String stringField;

    @Test
    @DisplayName("randomEnum returns a valid enum constant from the field type")
    void testRandomEnum() throws Exception {
        Field field = this.getClass().getDeclaredField("dummyField");

        // Repeat a few times to ensure we can hit different values potentially
        for (int i = 0; i < 10; i++) {
            DummyEnum result = Utils.randomEnum(field);
            assertNotNull(result);
            assertTrue(result == DummyEnum.ONE || result == DummyEnum.TWO || result == DummyEnum.THREE);
        }
    }

    @Test
    @DisplayName("randomEnum throws ClassCastException for non-enum fields")
    void testRandomEnumWithNonEnumField() throws Exception {
        Field field = this.getClass().getDeclaredField("stringField");

        assertThrows(ClassCastException.class, () -> Utils.randomEnum(field));
    }
}