# Dummy Object Factory (Java)

This library provides a simple way to create instances of Java objects using dummy data through a factory. It’s ideal for unit testing, rapid development, and 
scenarios where you need a large number of objects with predefined data.

## Key Features

*   **Dynamic Object Creation:** Allows you to create objects of any Java class based on its name.
*   **Customizable Dummy Data:** Defines the dummy data for each class, with the ability to use different data types (String, Integer, Boolean, etc.).
*   **Simple Configuration:** An easy-to-use interface for defining the properties of each dummy object.
*   **Flexibility:** Designed to be used with any Java class.
*   **Facilitates Unit Testing:** Generates objects with predictable data, simplifying the creation of test cases.

## Installation

1.  **Add the Dependency:**

    You can add the library to your Maven project:

    **Maven:**

    ```xml
    <dependency>
        <groupId>com.inad.dummyobjects</groupId>
        <artifactId>dummy-objects</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

## Usage

1.  **Import the Library:** Import the library's package into your class.
2.  **Create Objects:** Use the `Factory.create(Class<T>)` static method to create an object of the specified class.
3.  **For Lists:** Use `Factory.create(Class<T>, int)` static method to create a list of object for the specified class.

## Factory Class

This class centralizes object creation.

*   `create(Class<T>)`: Method that creates an instance of the specified class.
*   `create(Class<T>, int)`: Method that creates a List of instances for the specified class.

## Configuration

The factory not needs information about the classes to be created and the dummy data to be used for each.

## Example Usage

```java
import com.inad.dummyobjects.Factory;
import com.inad.dummyobjects.dto.Person;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create an object of the Person class
        Person person = Factory.create(Person.class);

        // 2. Print the object's data
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // 3. Create a List of object for Person class
        List<Person> persons = Factory.create(Person.class, 3);

        // 4. Print list
        for (Person p : persons) {
            System.out.println("Name: " + p.getName());
            System.out.println("Age: " + p.getAge());
        }
    }
}
```

##  License

Apache License Version 2.0, January 2004

## Contact

[Andrés Gasca]

[andresg1006@gmail.com]
