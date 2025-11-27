package com.inad;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.inad.Utils.*;

public class Factory {

    private static final String LETTERS = "ABCDEFHIJKLMNOPQRSTUVWXYZ -";

    public static <T> List<T> create(final Class<T> className, final Integer size) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(create(className));
        }
        return list;
    }

    private static <T> T create(final Class<T> className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        final T instance = className.getDeclaredConstructor().newInstance();
        final Field[] fields = className.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getType().isEnum()) {
                field.set(instance, randomEnum(field));
            } else if (field.getType().isPrimitive()) {
                setPrimitive(instance, field);
            } else {
                setObject(instance, field);
            }
        }
        return instance;
    }

    private static <T> void setPrimitive(final T instance, final Field field) throws IllegalAccessException {
        switch (field.getType().getSimpleName()) {
            case "int" -> field.set(instance, randomNumber(1, 10));
            case "long" ->  field.set(instance, randomNumber(100L, 1000L));
            case "float" -> field.set(instance, randomNumber(10f, 100f));
            case "double" -> field.set(instance, randomNumber(1.0, 10.0));
            case "boolean" -> field.set(instance, randomBoolean());
            default -> {}
        }
    }

    private static <T> void setObject(final T instance, final Field field) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        field.setAccessible(true);
        switch (field.getType().getSimpleName()) {
            case "String" -> field.set(instance, randomString(10, LETTERS));
            case "Integer" -> field.set(instance, randomNumber(1, 10));
            case "Long" ->  field.set(instance, randomNumber(100L, 1000L));
            case "Float" -> field.set(instance, randomNumber(10f, 100f));
            case "Double" -> field.set(instance, randomNumber(1.0, 10.0));
            case "Boolean" -> field.set(instance, randomBoolean());
            case "Date" -> field.set(instance, new Date());
            default -> {
                String className = field.getType().getName();
                Class<?> classObject = Class.forName(className);
                Object obj = create(classObject);
                field.set(instance, obj);
            }
        }
    }
}