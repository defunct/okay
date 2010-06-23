package com.goodworkalan.stringbeans.validation.annotations;

import java.lang.annotation.Annotation;

import com.goodworkalan.reflective.Reflective;
import com.goodworkalan.reflective.getter.Getter;
import com.goodworkalan.reflective.getter.Getters;
import com.goodworkalan.stringbeans.validation.Mistakes;
import com.goodworkalan.stringbeans.validation.ObjectValidator;
import com.goodworkalan.stringbeans.validation.ReflectiveValidator;
import com.goodworkalan.stringbeans.validation.ValidationException;

/**
 * An implementation of validator that validates any Java bean based on 
 * annotations applied to the bean properties or fields.
 *
 * @author Alan Gutierrez
 */
public class AnnotationValidator extends ReflectiveValidator implements ObjectValidator<Object> {
    /**
     * Validate the given bean based on annotations applied to the bean
     * properties or fields, reporting errors in the given map of mistakes.
     * 
     * @param object
     *            The object to validate.
     * @param mistakes
     *            The collection of mistakes.
     */
    public void validate(Object object, Mistakes mistakes) {
        for (Getter getter : Getters.getGetters(object.getClass()).values()) {
            boolean gotten = false;
            Object got = null;
            for (Annotation annotation : getter.getAccessibleObject().getAnnotations()) {
                Validation validation = annotation.getClass().getAnnotation(Validation.class);
                if (validation != null) {
                    if (!gotten) {
                        got = get(getter, object);
                        gotten = true;
                    }
                    AnnotationGetterValidator validator = getValidator(validation);
                    validator.validate(object, getter, got, mistakes);
                }
            }
        }
    }

    /**
     * Create an instance of the validation implementation for the given
     * validation annotation. This method is extracted in order to test
     * reflection exception handling.
     * 
     * @param validation
     *            The validation.
     * @return An instance of the validation implementation.
     */
    static AnnotationGetterValidator getValidator(Validation validation) {
        try {
            return validation.validator().newInstance();
        } catch (Throwable e) {
            throw new ValidationException(AnnotationValidator.class, "validator", Reflective.encode(e));
        }
    }

    /**
     * Get a property from the given object using the given getter. This method
     * is extracted in order to test reflection exception handling.
     * 
     * @param getter
     *            The getter.
     * @param object
     *            The object.
     * @return The object property.
     */
    static Object get(Getter getter, Object object) {
        try {
            return getter.get(object);
        } catch (Throwable e) {
            throw new ValidationException(AnnotationValidator.class, "get", Reflective.encode(e));
        }
    }
}
