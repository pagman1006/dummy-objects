package com.inad.dummyobjects;

import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    public enum TestEnum {
        ITEM_1, ITEM_2
    }

    // Inner static class to be used as a nested object type
    @Getter
    public static class InnerDto {
        private String name;
        private int number;

    }

    // Main DTO to test all supported field types
    @Getter
    public static class DummyDto {
        // Getters
        private String stringVal;
        private int intVal;
        private long longVal;
        private float floatVal;
        private double doubleVal;
        private boolean booleanVal;

        private Integer integerWrapper;
        private Long longWrapper;
        private Float floatWrapper;
        private Double doubleWrapper;
        private Boolean booleanWrapper;

        private BigDecimal bigDecimalVal;

        private Date dateVal;
        private Instant instantVal;
        private Timestamp timestampVal;
        private LocalDate localDateVal;
        private LocalDateTime localDateTimeVal;
        private LocalTime localTimeVal;

        private TestEnum enumVal;

        private InnerDto innerDto;

        private List<InnerDto> listDto;

    }

    @Test
    @DisplayName("Factory.create(Class) should populate all supported fields correctly")
    void testCreateObject() {
        DummyDto dto = Factory.create(DummyDto.class);

        assertNotNull(dto, "Created object should not be null");

        // 1. Verify Primitives (Checking generic ranges from known implementation)
        // Note: Factory uses specific ranges (e.g., 1-10 for int), so 0 should not appear.
        assertTrue(dto.getIntVal() >= 1, "intVal should be >= 1");
        assertTrue(dto.getLongVal() >= 100L, "longVal should be >= 100");
        assertTrue(dto.getFloatVal() >= 10f, "floatVal should be >= 10.0");
        assertTrue(dto.getDoubleVal() >= 1.0, "doubleVal should be >= 1.0");
        // Boolean can be true or false, just ensure no exception occurred.

        // 2. Verify Wrappers and String
        assertNotNull(dto.getStringVal(), "String field should not be null");
        assertFalse(dto.getStringVal().isEmpty(), "String field should not be empty");

        assertNotNull(dto.getIntegerWrapper(), "Integer wrapper should not be null");
        assertNotNull(dto.getLongWrapper(), "Long wrapper should not be null");
        assertNotNull(dto.getFloatWrapper(), "Float wrapper should not be null");
        assertNotNull(dto.getDoubleWrapper(), "Double wrapper should not be null");
        assertNotNull(dto.getBooleanWrapper(), "Boolean wrapper should not be null");

        assertNotNull(dto.getBigDecimalVal(), "BigDecimal field should not be null");

        // 3. Verify Date/Time types
        assertNotNull(dto.getDateVal(), "Date field should not be null");
        assertNotNull(dto.getInstantVal(), "Instant field should not be null");
        assertNotNull(dto.getTimestampVal(), "Timestamp field should not be null");
        assertNotNull(dto.getLocalDateVal(), "LocalDate field should not be null");
        assertNotNull(dto.getLocalDateTimeVal(), "LocalDateTime field should not be null");
        assertNotNull(dto.getLocalTimeVal(), "LocalTime field should not be null");

        // 4. Verify Enum
        assertNotNull(dto.getEnumVal(), "Enum field should not be null");
        assertTrue(dto.getEnumVal() == TestEnum.ITEM_1 || dto.getEnumVal() == TestEnum.ITEM_2,
                "Enum value should be one of the defined constants");

        // 5. Verify Nested Object (Recursion)
        assertNotNull(dto.getInnerDto(), "Nested object should not be null");
        assertNotNull(dto.getInnerDto().getName(), "Nested object's fields should be populated");

        // 6. Verify List
        assertNotNull(dto.getListDto(), "List field should not be null");
        assertFalse(dto.getListDto().isEmpty(), "List should not be empty");
        assertNotNull(dto.getListDto().get(0), "List elements should not be null");
        assertNotNull(dto.getListDto().get(0).getName(), "List element fields should be populated");
    }

    @Test
    @DisplayName("Factory.create(Class, int) should create a list of objects of specific size")
    void testCreateList() {
        int size = 5;
        List<DummyDto> list = Factory.create(DummyDto.class, size);

        assertNotNull(list, "Resulting list should not be null");
        assertEquals(size, list.size(), "List size should match the requested size");

        for (DummyDto item : list) {
            assertNotNull(item, "List items should not be null");
            assertNotNull(item.getStringVal(), "List items should have populated fields");
            assertNotNull(item.getInnerDto(), "List items should have populated nested objects");
        }
    }

}