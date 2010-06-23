package com.goodworkalan.okay.annotations;


import com.goodworkalan.okay.Mistakes;
import com.goodworkalan.reflective.getter.Getter;

/**
 * Implementation of the not null validation.
 *
 * @author Alan Gutierrez
 */
public class ValidatePresenceOfValidator implements AnnotationGetterValidator {
    /**
     * Validate that the value returned by the getter is not null.
     * 
     * @param object
     *            The object to validate.
     * @param getter
     *            The property getter.
     * @param got
     *            The value returned from the property getter.
     * @param mistakes
     *            The collection of mistakes.
     */
    public void validate(Object object, Getter getter, Object got, Mistakes mistakes) {
        if (got == null) {
            mistakes.mistake(object, getter.getName(), "required", getter.getName());
        }
    }
}
