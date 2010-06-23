package com.goodworkalan.okay.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Applied to an annotation type, this annotation indicates that an annotation
 * is a validation annotation and defined the validation implementation for the
 * validation.
 * 
 * @author Alan Gutierrez
 */
@Documented
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {
    /** The validation implementation. */
    Class<? extends AnnotationGetterValidator> validator();
}
