package com.goodworkalan.okay.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** Validate that a field is not null. */
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Validation(validator = ValidatePresenceOfValidator.class)
public @interface ValidatePresenceOf {
}
