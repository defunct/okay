package com.goodworkalan.stringbeans.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Validate the numeric range of a field.
 *  
 * @author Alan Gutierrex
 */
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Validation(validator = ValidateRangeValidator.class)
public @interface ValidateRange {
    /** The minimum value for the annotated field. */
    public double min() default Double.MIN_VALUE;

    /** The maximum value for the annotated field. */
    public double max() default Double.MAX_VALUE;
}
