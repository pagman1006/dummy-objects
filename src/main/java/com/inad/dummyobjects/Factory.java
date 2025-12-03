package com.inad.dummyobjects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.inad.dummyobjects.Utils.randomBoolean;
import static com.inad.dummyobjects.Utils.randomEnum;
import static com.inad.dummyobjects.Utils.randomNumber;
import static com.inad.dummyobjects.Utils.randomString;

public class Factory {

    private static final String LETTERS = "ABCDEFHIJKLMNOPQRSTUVWXYZ -";
    private static final String INT = "int";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String BOOLEAN = "boolean";
    private static final String STRING = "string";
    private static final String INTEGER = "integer";
    private static final String DATE = "date";
    private static final String INSTANT = "instant";
    private static final String TIMESTAMP = "timestamp";
    private static final String LOCAL_DATE = "localdate";
    private static final String LOCAL_DATE_TIME = "localdatetime";
    private static final String LOCAL_TIME = "localtime";

    private static final int SIZE_LIST = 3;

    private static final String IS_COLLECTION_LOG = "isCollection";
    private static final String CLASS_NAME_LOG = "ClassName: ";

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
        System.out.println(IS_COLLECTION_LOG);
        String cName = getGenericClassName(field.getGenericType());
        Class<?> classObject = Class.forName(cName);
        List<?> obj = create(classObject, SIZE_LIST);
        field.set(instance, obj);
    }

    private static <T> void setPrimitive(final T instance, final Field field) throws IllegalAccessException {
        switch (field.getType().getSimpleName()) {
            case INT -> field.set(instance, randomNumber(1, 10));
            case LONG -> field.set(instance, randomNumber(100L, 1000L));
            case FLOAT -> field.set(instance, randomNumber(10f, 100f));
            case DOUBLE -> field.set(instance, randomNumber(1.0, 10.0));
            case BOOLEAN -> field.set(instance, randomBoolean());
            default -> {
            }
        }
    }

    private static <T> void setObject(final T instance, final Field field)
            throws IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException {
        field.trySetAccessible();
        final String typeName = field.getType().getSimpleName().toLowerCase();
        switch (typeName) {
            case STRING -> field.set(instance, randomString(10, LETTERS));
            case INTEGER -> field.set(instance, randomNumber(1, 10));
            case LONG -> field.set(instance, randomNumber(100L, 1000L));
            case FLOAT -> field.set(instance, randomNumber(10f, 100f));
            case DOUBLE -> field.set(instance, randomNumber(1.0, 10.0));
            case BOOLEAN -> field.set(instance, randomBoolean());
            case DATE -> field.set(instance, new Date());
            case INSTANT -> field.set(instance, new Date().toInstant());
            case TIMESTAMP -> field.set(instance, new Timestamp(System.currentTimeMillis()));
            case LOCAL_DATE -> field.set(instance, LocalDate.now());
            case LOCAL_DATE_TIME -> field.set(instance, LocalDateTime.now());
            case LOCAL_TIME -> field.set(instance, LocalDateTime.now().toLocalTime());
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
        System.out.println(CLASS_NAME_LOG.concat(className));
        return className;
    }

}