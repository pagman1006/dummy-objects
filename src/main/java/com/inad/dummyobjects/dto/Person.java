package com.inad.dummyobjects.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class Person {

    private String name;
    private String lastName;
    private Integer age;
    private Boolean isMale;
    private boolean isSingle;
    private long height;
    private Date birthDate;
    private List<Phone> phones;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private Timestamp timestamp;
}