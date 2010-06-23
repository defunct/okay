package com.goodworkalan.okay;

/**
 * Validates an object recording errors in a collection of mistakes. This
 * is the common interface for all validation strategies. Validation strategies
 * are associated with a class in a {@link Validator}.
 * 
 * @see Validator
 */
public interface ObjectValidator<T> {
    /**
     * Validate the given bean reporting errors in the given map of mistakes.
     * 
     * @param object
     *            The object to validate.
     * @param mistakes
     *            The collection of mistakes.
     */
    public void validate(T object, Mistakes mistakes);
}
