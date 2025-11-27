package com.inad;

import com.inad.dto.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Person> persons = Factory.create(Person.class, 3);
            System.out.println("Instancias Creadas: " + persons.size());
            for(Person person : persons) {
                System.out.println(person);
            }
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}