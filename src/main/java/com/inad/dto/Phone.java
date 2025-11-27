package com.inad.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Phone {

    private String number;
    private PhoneType phoneType;

}