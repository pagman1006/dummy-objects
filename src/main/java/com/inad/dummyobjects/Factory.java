package com.inad.dummyobjects;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.inad.dummyobjects.constants.Constants.*;
import static com.inad.dummyobjects.util.Utils.*;

/**
 * Generates populated instances of arbitrary Java classes for testing and prototyping.
 * <p>
 * The factory uses reflection to inspect a target type and assign synthetic values to its
 * fields or record components based on the declared Java type. It supports primitive types,
 * common value types such as {@link String}, {@link java.util.Date}, numeric wrappers, enums,
 * collections, and nested object graphs that can be instantiated without explicit constructor
 * arguments.
 * </p>
 * <p>
 * This utility is primarily intended for creating sample data in unit tests and mock scenarios,
 * where a fully populated object is needed without requiring a dedicated builder or fixture class.
 * </p>
 */
@SuppressWarnings("unchecked")
public class Factory {

    /**
     * Creates a list of dummy objects of the specified class type, populated with random data.
     *
     * @param className The class of the objects to be created.
     * @param size      The number of objects to create in the list.
     * @param <T>       The type of the objects.
     * @return A list containing {@code size} instances of {@code className} populated with random data.
     */
    public static <T> List<T> create(final Class<T> className, final int size) {
        final List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(create(className));
        }
        return list;
    }

    /**
     * Creates a single instance of the specified class populated with random data.
     *
     * @param className The class of the object to create.
     * @param <T>       The type of the object.
     * @return An instance of {@code className} with populated fields.
     * @throws RuntimeException if instantiation, field access, or other reflection operations fail.
     */
    public static <T> T create(final Class<T> className) {
        try {
            return className.isRecord() ? createRecord(className) : createObject(className, false);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a record instance by invoking its canonical constructor with values generated for each component.
     *
     * @param clazz The record type to instantiate.
     * @param <T> The record type.
     * @return A new record instance containing generated values for each component.
     * @throws NoSuchMethodException If the record does not expose a constructor matching its components.
     * @throws InvocationTargetException If the constructor throws an exception.
     * @throws InstantiationException If the record cannot be instantiated.
     * @throws IllegalAccessException If constructor access is denied.
     * @throws ClassNotFoundException If a nested generic or referenced type cannot be resolved.
     */
    private static <T> T createRecord(Class<T> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        final RecordComponent[] recordComponents = clazz.getRecordComponents();

        Class<?>[] parameterTypes = Arrays.stream(recordComponents)
                .map(RecordComponent::getType)
                .toArray(Class<?>[]::new);

        Object[] objects = Arrays.stream(recordComponents)
                .map(recordComponent -> {
                    try {
                        return setObjectComponent(recordComponent);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray();

        Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(objects);
    }

    /**
     * Resolves a record component to a randomly generated value compatible with its declared type.
     *
     * @param recordComponent The component being populated.
     * @param <T> The inferred type for the generated value.
     * @return A generated value for the component.
     * @throws ClassNotFoundException If the component's generic type cannot be loaded.
     */
    private static <T> T setObjectComponent(RecordComponent recordComponent) throws ClassNotFoundException {
        if (recordComponent.getType().isEnum()) {
            return (T) randomEnum(recordComponent.getType());
        } else if (recordComponent.getType().isPrimitive()) {
            return generatePrimitive(recordComponent.getType());
        } else if (Collection.class.isAssignableFrom(recordComponent.getType())) {
            Type genericType = recordComponent.getGenericType();
            return generateList(getGenericClassName(genericType));
        } else {
            return (T) createObject(recordComponent.getType(), true);
        }
    }

    /**
     * Instantiates a class and fills its declared fields with generated values.
     *
     * @param clazz The type to create.
     * @param isRecord Indicates whether the target type is a record and should be created via its constructor.
     * @param <T> The type of object to instantiate.
     * @return A populated object instance.
     * @throws ClassNotFoundException If a nested or collection element type cannot be resolved.
     */
    private static <T> T createObject(final Class<T> clazz, final boolean isRecord) throws ClassNotFoundException {
        if (isRecord) {
            return createObject(clazz);
        } else {
            try {
                final T instance = clazz.getDeclaredConstructor().newInstance();
                final Field[] fields = clazz.getDeclaredFields();
                for (final Field field : fields) {
                    field.trySetAccessible();
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        field.set(instance, generateList(getGenericClassName(field.getGenericType())));
                    } else {
                        field.set(instance, createObject(field.getType()));
                    }
                }
                return instance;
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Generates a value for a field or nested type based on its declared Java type.
     *
     * @param clazz The type to generate.
     * @param <T> The generated value type.
     * @return A value compatible with the supplied class.
     * @throws ClassNotFoundException If the type requires dynamic loading that fails.
     */
    private static <T> T createObject(final Class<?> clazz) throws ClassNotFoundException {
        if (clazz.isEnum()) {
            return (T) randomEnum(clazz);
        } else if (clazz.isPrimitive()) {
            return generatePrimitive(clazz);
        } else {
            return setObject(clazz);
        }
    }

    /**
     * Maps a Java type to a generated default value, including custom handling for supported JDK classes.
     *
     * @param clazz The declared type to map.
     * @param <T> The inferred object type.
     * @return A generated instance or scalar value matching the class.
     * @throws ClassNotFoundException If the type is not a supported built-in and cannot be dynamically loaded.
     */
    private static <T> T setObject(final Class<?> clazz) throws ClassNotFoundException {
        final String typeName = clazz.getSimpleName().toLowerCase();
        T obj = null;
        switch (typeName) {
            case STRING -> obj = (T) randomString(10, LETTERS);
            case INTEGER -> obj = (T) randomNumber(1, 10);
            case LONG -> obj = (T) randomNumber(1L, 10L);
            case FLOAT -> obj = (T) randomNumber(10f, 100f);
            case DOUBLE -> obj = (T) randomNumber(10d, 100d);
            case BIG_DECIMAL -> obj = (T) new BigDecimal(randomNumber(10, 100));
            case BOOLEAN -> obj = (T) randomBoolean();
            case DATE -> obj = (T) new Date();
            case INSTANT -> obj = (T) new Date().toInstant();
            case TIMESTAMP -> obj = (T) new Timestamp(System.currentTimeMillis());
            case LOCAL_DATE -> obj = (T) LocalDate.now();
            case LOCAL_DATE_TIME -> obj = (T) LocalDateTime.now();
            case LOCAL_TIME -> obj = (T) LocalDateTime.now().toLocalTime();
            default -> {
                final String className = clazz.getTypeName();
                final Class<?> classObject = Class.forName(className);
                obj = (T) create(classObject);
            }
        }
        return obj;
    }


    /**
     * Creates a random primitive value for supported numeric and boolean types.
     *
     * @param clazz The primitive type to generate.
     * @param <T> The primitive wrapper type.
     * @param <U> The value produced from the primitive generation.
     * @return A random primitive value or boolean.
     */
    private static <T, U> U generatePrimitive(Class<T> clazz) {
        U object = null;
        switch (clazz.getTypeName().toLowerCase()) {
            case INT -> object = (U) randomNumber(1, 100);
            case LONG -> object = (U) randomNumber(10L, 1000L);
            case FLOAT -> object = (U) randomNumber(1f, 100f);
            case DOUBLE -> object = (U) randomNumber(1d, 100d);
            case BOOLEAN -> object = (U) randomBoolean();
        }
        return object;
    }

    /**
     * Generates a list of randomly populated elements for the provided generic type.
     *
     * @param className The fully qualified or simple type name of the list element.
     * @param <T> The resulting list type.
     * @return A list whose elements are generated according to the provided type.
     * @throws ClassNotFoundException If the element type cannot be resolved.
     */
    private static <T> T generateList(final String className) throws ClassNotFoundException {
        final Class<?> classObject = Class.forName(className);
        final List<?> obj = create(classObject, SIZE_LIST);
        return (T) obj;
    }

    /**
     * Extracts the generic class name from a parameterized type (e.g., getting "String" from "List<String>").
     *
     * @param genericType The type to inspect.
     * @return The name of the generic class.
     */
    private static String getGenericClassName(final Type genericType) {
        String className = genericType.getTypeName();
        className = className.substring(className.indexOf(MINUS_THAN) + 1, className.lastIndexOf(MAJOR_THAN));
        return className;
    }

}