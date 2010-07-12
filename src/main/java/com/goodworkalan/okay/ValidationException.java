package com.goodworkalan.okay;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Used contextual messages since there are only a few errors and those have to
 * do with reflection, so they are generally unrecoverable. There is no
 * connection to the network or writing to file, so most errors are going to be
 * unrecoverable, problems with reflection, missing classes, and casts.
 * 
 * @author Alan Gutierrez
 */
public class ValidationException extends RuntimeException {
    /** Serial version id. */
    private static final long serialVersionUID = -1L;
    
    /** The message context class. */
    public final Class<?> contextClass;
    
    /** The message error code. */
    public final String code;

    /**
     * Create a validation exception with the given context class, the given string
     * error code and the given positioned parameters.
     * 
     * @param context
     *            The error context.
     * @param code
     *            The error code.
     * @param arguments
     *            The positioned arguments to the error format string.
     */
    public ValidationException(Class<?> context, String code, Object...arguments) {
        this(null, context, code, arguments);
    }

    /**
     * Create a validation exception with the given context class, the given
     * string error code, the given cause and the given positioned parameters.
     * 
     * @param context
     *            The error context.
     * @param code
     *            The error code.
     * @param arguments
     *            The positioned arguments to the error format string.
     */
    public ValidationException(Throwable cause, Class<?> contextClass, String code, Object...arguments) {
        super(formatMessage(contextClass, code, arguments), cause);
        this.contextClass = contextClass;
        this.code = code;
    }

    /**
     * Format the exception message using the message arguments to format the
     * message found with the message key in the message bundle found in the
     * package of the given context class.
     * 
     * @param contextClass
     *            The context class.
     * @param code
     *            The error code.
     * @param arguments
     *            The format message arguments.
     * @return The formatted message.
     */
    private final static String formatMessage(Class<?> contextClass, String code, Object...arguments) {
        String baseName = contextClass.getPackage().getName() + ".exceptions";
        String messageKey = contextClass.getSimpleName() + "/" + code;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.getDefault(), Thread.currentThread().getContextClassLoader());
            return String.format((String) bundle.getObject(messageKey), arguments);
        } catch (Exception e) {
            return String.format("Cannot load message key [%s] from bundle [%s] becuase [%s].", messageKey, baseName, e.getMessage());
        }
    }
}