package com.inad.dummyobjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Dummy DTO class for testing.
 */
@Getter
public class DummyDto {

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

    private FactoryTest.TestEnum enumVal;

    private FactoryTest.InnerDto innerDto;

    private List<FactoryTest.InnerDto> listDto;

}
