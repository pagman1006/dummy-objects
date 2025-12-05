package com.inad.dummyobjects.constants;

/**
 * Constants used for logging within the library.
 * <p>
 * This class contains static final strings that serve as log messages or prefixes
 * for logging operations, primarily used during the object creation process to trace
 * execution flow (e.g., identifying collections or class names).
 * </p>
 */
public class ConstantsLog {

    /**
     * Log message used when the factory detects that a field is a Collection.
     */
    public static final String IS_COLLECTION_LOG = "isCollection";

    /**
     * Log prefix used when printing the class name extracted from a generic type.
     */
    public static final String CLASS_NAME_LOG = "ClassName: ";

}
