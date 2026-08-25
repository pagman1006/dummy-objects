package com.inad.dummyobjects.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Class representing a phone number with a type.
 */
@Data
@ToString
public class Phone {

    /**
     * The phone number.
     */
    private String number;
    /**
     * The type of the phone number.
     */
    private PhoneType phoneType;

}