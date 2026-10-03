package com.inad.dummyobjects.dto;

import java.time.LocalDate;
import java.util.List;

public record PersonRecord(String name, String lastName, int age, int height, int weight, LocalDate birthDate,
                           List<Phone> phones) {
}
