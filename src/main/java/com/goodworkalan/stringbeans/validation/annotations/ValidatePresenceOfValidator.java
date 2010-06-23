package com.goodworkalan.stringbeans.validation.annotations;


import com.goodworkalan.reflective.getter.Getter;
import com.goodworkalan.stringbeans.validation.Mistakes;

// TODO Document.
public class ValidatePresenceOfValidator implements AnnotationGetterValidator {
    // TODO Document.
    public void validate(Object object, Getter getter, Object got, Mistakes mistakes) {
        if (got == null) {
            mistakes.mistake(object, getter.getName(), "required", getter.getName());
        }
    }
}
