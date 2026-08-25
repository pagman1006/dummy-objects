package com.inad.dummyobjects.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Class representing a person with various attributes.
 */
@Data
@ToString
public class Person {

    /**
     * The name of the person.
     */
    private String name;
    /**
     * The last name of the person.
     */
    private String lastName;
    /**
     * The age of the person.
     */
    private Integer age;
    /**
     * The gender of the person.
     */
    private Boolean isMale;
    /**
     * The marital status of the person.
     */
    private boolean isSingle;
    /**
     * The height of the person in meters.
     */
    private long height;
    /**
     * The weight of the person in kilograms.
     */
    private double weight;
    /**
     * The date of birth of the person.
     */
    private Date birthDate;
    /**
     * The list of phones associated with the person.
     */
    private List<Phone> phones;
    /**
     * LocalDateTime, LocalDate, LocalTime, Timestamp
     */
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private Timestamp timestamp;
}