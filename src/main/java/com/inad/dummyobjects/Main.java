package com.inad.dummyobjects;

import com.inad.dummyobjects.dto.Person;
import com.inad.dummyobjects.dto.PersonRecord;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final List<Person> persons = Factory.create(Person.class, 3);
        System.out.println("Instances Created: " + persons.size());
        for (final Person person : persons) {
            System.out.println(person);
        }

        System.out.println("Generating PersonRecord instance");
        final PersonRecord personRecord = Factory.create(PersonRecord.class);
        System.out.println(personRecord);

    }
}