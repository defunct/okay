package com.goodworkalan.stringbeans.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.goodworkalan.reflective.ReflectiveException;
import com.goodworkalan.reflective.getter.Getter;
import com.goodworkalan.reflective.getter.Getters;

/**
 * Validate the presence of bean properties or fields using reflection.
 * 
 * @author Alan Gutierrez
 *
 * @param <T> The type to validate.
 */
public class Required<T> implements ObjectValidator<T> {
    /** The set of field names to validate. */
    private final Set<String> fieldNames;

    /**
     * The property or field names to validate the presence of.
     * 
     * @param fieldNames
     *            The field names.
     */
    public Required(String...fieldNames) {
        this.fieldNames = new HashSet<String>(Arrays.asList(fieldNames));
    }

    /**
     * Validate that the values of the field names are not null.
     * 
     * @param object
     *            The object to validate.
     * @param mistakes
     *            The collection of mistakes.
     */
    public void validate(T object, Mistakes mistakes) {
        for (Getter getter : Getters.getGetters(object.getClass()).values()) {
            if (fieldNames.contains(getter.getName())) {
                try {
                    if (getter.get(object) == null) {
                        mistakes.mistake(object, getter.getName(), "required");
                    }
                } catch (ReflectiveException e) {
                    throw new ValidationException(Required.class, "getter", getter.getName(), getter.getType(), object.getClass());
                }
            }
        }
    }
}
