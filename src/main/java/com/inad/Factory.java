package com.inad;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.inad.Utils.randomBoolean;
import static com.inad.Utils.randomEnum;
import static com.inad.Utils.randomNumber;
import static com.inad.Utils.randomString;

public class Factory {

    private static final String LETTERS = "ABCDEFHIJKLMNOPQRSTUVWXYZ -";

    public static <T> List<T> create(final Class<T> className, final int size) {
        final List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(create(className));
        }
        return list;
    }

    private static <T> T create(final Class<T> className) {
        try {
            final T instance = className.getDeclaredConstructor().newInstance();
            final Field[] fields = className.getDeclaredFields();

            for (Field field : fields) {
                field.trySetAccessible();

                if (field.getType().isEnum()) {
                    field.set(instance, randomEnum(field));
                } else if (Collection.class.isAssignableFrom(field.getType())) {
                    setList(instance, field);
                } else if (field.getType().isPrimitive()) {
                    setPrimitive(instance, field);
                } else {
                    setObject(instance, field);
                }
            }
            return instance;
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void setList(final T instance, final Field field)
            throws ClassNotFoundException, IllegalAccessException {
        System.out.println("isCollection");
        String cName = getGenericClassName(field.getGenericType());
        Class<?> classObject = Class.forName(cName);
        List<?> obj = create(classObject, 3);
        field.set(instance, obj);
    }

    private static <T> void setPrimitive(final T instance, final Field field) throws IllegalAccessException {
        switch (field.getType().getSimpleName()) {
            case "int" -> field.set(instance, randomNumber(1, 10));
            case "long" -> field.set(instance, randomNumber(100L, 1000L));
            case "float" -> field.set(instance, randomNumber(10f, 100f));
            case "double" -> field.set(instance, randomNumber(1.0, 10.0));
            case "boolean" -> field.set(instance, randomBoolean());
            default -> {
            }
        }
    }

    private static <T> void setObject(final T instance, final Field field)
            throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException {
        field.trySetAccessible();
        switch (field.getType().getSimpleName()) {
            case "String" -> field.set(instance, randomString(10, LETTERS));
            case "Integer" -> field.set(instance, randomNumber(1, 10));
            case "Long" -> field.set(instance, randomNumber(100L, 1000L));
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

    private static String getGenericClassName(final Type genericType) {
        String className = genericType.getTypeName();
        className = className.substring(className.indexOf("<") + 1, className.lastIndexOf(">"));
        System.out.println("ClassName: " + className);
        return className;
    }

}