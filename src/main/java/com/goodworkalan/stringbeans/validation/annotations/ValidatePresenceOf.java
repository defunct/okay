package com.goodworkalan.stringbeans.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// TODO Document.
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Validation(validator = ValidatePresenceOfValidator.class)
public @interface ValidatePresenceOf {
}
