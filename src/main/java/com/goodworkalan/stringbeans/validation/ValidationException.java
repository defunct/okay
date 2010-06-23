package com.goodworkalan.stringbeans.validation;

import com.goodworkalan.danger.ContextualDanger;

/**
 * Used contextual messages since there are only a few errors and those have to
 * do with reflection, so they are generally unrecoverable. There is no
 * connection to the network or writing to file, so most errors are going to be
 * unrecoverable, problems with reflection, missing classes, and casts.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class ValidationException extends ContextualDanger {
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
        super(context, code, null);
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
    public ValidationException(Class<?> context, String code, Throwable cause, Object...arguments) {
        super(context, code, cause);
    }
}