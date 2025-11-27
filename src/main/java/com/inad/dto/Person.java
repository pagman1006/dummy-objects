package com.inad.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Person {

    private String name;
    private String lastName;
    private Integer age;
    private Boolean isMale;
    private boolean isSingle;
    private long height;
    private Date bithday;
    private Phone phone;
}