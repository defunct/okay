package com.goodworkalan.stringbeans.validation.annotations;

import com.goodworkalan.reflective.getter.Getter;
import com.goodworkalan.stringbeans.validation.Mistakes;

/**
 * Implementation of a validation strategy declared by a validation annotation.
 * 
 * @author Alan Gutierrez
 */
public interface AnnotationGetterValidator {
    /**
     * Validate a value obtained from a getter against the given object
     * reporting errors in the given collection of mistakes. The getter is
     * provided so that the validator can reference the getter member name and
     * annotations, but the getter is invoked prior to calling the validate
     * method to centralize reflection exception handling.
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
    public void validate(Object object, Getter getter, Object got, Mistakes mistakes);
}
