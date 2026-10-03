# Dummy Objects

A lightweight Java library for generating populated dummy objects for tests, prototypes, and mock data scenarios.

The project uses reflection to inspect Java classes and records, then fills their fields or record components with random values such as strings, numbers, booleans, dates, enums, collections, and nested objects.

## Features

- Create a populated instance of any class with `Factory.create(Class<T>)`
- Create a collection of populated instances with `Factory.create(Class<T>, int)`
- Supports primitive and wrapper types
- Supports `String`, `Date`, `BigDecimal`, `Instant`, `LocalDate`, `LocalDateTime`, `LocalTime`, and `Timestamp`
- Supports enums, lists, and nested object graphs
- Works with Java records as well as regular classes

## Installation

Add the dependency to your Maven project:

```xml
<dependency>
    <groupId>io.github.pagman1006</groupId>
    <artifactId>dummy-objects</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Usage

Import the factory and generate sample objects directly from a class reference:

```java
import com.inad.dummyobjects.Factory;
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
```

Example model:

```java
public class Person {
    private String name;
    private int age;
    private boolean active;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }
}
```

Example record:

```java
public record PersonRecord(String name, int age, boolean active) {
}
```

The library populates the fields automatically as long as the class is instantiable and uses supported types.

## Supported Types

The default factory logic handles common Java types, including:

- primitive and boxed numeric types
- `String`
- `boolean` / `Boolean`
- `Date`
- `BigDecimal`
- `Instant`
- `Timestamp`
- `LocalDate`
- `LocalDateTime`
- `LocalTime`
- enums
- Java records
- `List` and other collection interfaces backed by generated elements
- nested POJOs and records

## Notes

This library is intended for testing and development workflows where you need realistic-looking sample data without writing custom builders or fixture classes.

## License

Apache License Version 2.0, January 2004

## Contact

Andrés Gasca  
andresg1006@gmail.com
