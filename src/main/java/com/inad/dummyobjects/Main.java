package com.inad.dummyobjects;

import com.inad.dummyobjects.dto.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final List<Person> persons = Factory.create(Person.class, 3);
        System.out.println("Instances Created: " + persons.size());
        for (final Person person : persons) {
            System.out.println(person);
        }

    }
}