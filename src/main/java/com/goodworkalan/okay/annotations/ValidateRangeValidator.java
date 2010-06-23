package com.goodworkalan.okay.annotations;

import com.goodworkalan.okay.Mistakes;
import com.goodworkalan.reflective.getter.Getter;

/**
 * Implementation of numeric range validation.
 *
 * @author Alan Gutierrez
 */
public class ValidateRangeValidator implements AnnotationGetterValidator {
    /**
     * Validate that the given object is within the range defined by the
     * annotation.
     * <p>
     * If the type of number is a whole number, the minimum and maximum numbers
     * are converted to longs for comparison. Otherwise, the double values of
     * the minimum and maximum numbers are used.
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
        ValidateRange range = getter.getAccessibleObject().getAnnotation(ValidateRange.class);
        Number number = (Number) got;
        if ((got instanceof Float) || (got instanceof Double)) {
            if (number.doubleValue() > range.max()) {
                mistakes.mistake(object, getter.getName(), "range.maximum", number.longValue());
            } else if (number.doubleValue() < range.min()) {
                mistakes.mistake(object, getter.getName(), "range.minimum", number.longValue());
            }
        } else if (got instanceof Number) {
            if (number.longValue() > range.max()) {
                mistakes.mistake(object, getter.getName(), "range.maximum", number.longValue());
            } else if (number.longValue() < range.min()) {
                mistakes.mistake(object, getter.getName(), "range.minimum", number.longValue());
            }
        }
    }
}
