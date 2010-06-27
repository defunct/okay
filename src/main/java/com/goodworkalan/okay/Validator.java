package com.goodworkalan.okay;

import java.util.Collection;
import java.util.Map;

import com.goodworkalan.reflective.getter.Getter;
import com.goodworkalan.reflective.getter.Getters;
import com.goodworkalan.stringbeans.AbstractEmitter;
import com.goodworkalan.stringbeans.Converter;
import com.goodworkalan.utility.ClassAssociation;

/**
 * Validates an object graph.
 * 
 * @author Alan Gutierrez
 */
public class Validator {
    /** An association between classes and valiation strategies. */
    private final ClassAssociation<Validation<?>> association = new ClassAssociation<Validation<?>>();

    /**
     * Validate the given object.
     * 
     * @param object
     *            The object to validate.
     * @param mistakes
     *            The collection of mistakes discovered.
     */
    public void validate(Object object, Mistakes mistakes) {
        new Descent().validate(object, mistakes);
    }

    /**
     * Associate the given class with the given validation strategy.
     * 
     * @param <T>
     *            The type to validate.
     * @param type
     *            The class to validate.
     * @param validator
     *            The validation strategy.
     */
    public <T> void add(Class<T> type, ObjectValidator<T> validator) {
        association.exact(type, new Validation<T>(type, validator));
    }

    /**
     * Internal structure for validation that retains the generic type information.
     * 
     * @author Alan Gutierrez 
     *
     * @param <T> The type to validate.
     */
    private static class Validation<T> {
        /** The class to validate. */
        private final Class<T> objectClass;
        
        /** The validation strategy. */
        private final ObjectValidator<T> validator;

        /**
         * Create a validation association between the given class and the given
         * validation strategy.
         * 
         * @param objectClass
         *            The class to validate.
         * @param validator
         *            The validation strategy.
         */
        public Validation(Class<T> objectClass, ObjectValidator<T> validator) {
            this.objectClass = objectClass;
            this.validator = validator;
        }

        /**
         * Validate the given object reporting errors in the given collection of
         * mistakes.
         * 
         * @param object
         *            The class to validate.
         * @param mistakes
         *            The validation strategy.
         */
        public void validate(Object object, Mistakes mistakes) {
           validator.validate(objectClass.cast(object), mistakes);
        }
    }

    /**
     * Descends down an object tree validating objects using the validation
     * strategies of the owner class.
     * 
     * @author Alan Gutierrez
     */
    private class Descent extends AbstractEmitter<Mistakes, RuntimeException> {
        /** Construct a new descent. */
        public Descent() {
            super(new Converter());
        }

        /**
         * Validate the given bean with the validate strategies of the owner
         * class and validate each of the bean values.
         * 
         * @param output
         *            The collection of mistakes.
         * @param metaObject
         *            The bean getters.
         * @param bean
         *            The bean.
         */
        @Override
        protected void emitBean(Mistakes output, Object bean) {
            association.get(bean.getClass()).validate(bean, output);
            for (Getter getter : Getters.getGetters(bean.getClass()).values()) {
                emitObject(output, get(getter, bean));
            }
        }

        /**
         * Loops over the items, forwarding them to emit object to validate any
         * beans.
         * 
         * @param output
         *            The collection of mistakes.
         * @param map
         *            The map.
         */
        @Override
        protected void emitCollection(Mistakes output, Collection<?> collection) {
            for (Object object : collection) {
                emitObject(output, object);
            }
        }

        /**
         * Loops over the values, forwarding them to emit object to validate any
         * beans.
         * 
         * @param output
         *            The collection of mistakes.
         * @param map
         *            The map.
         */
        @Override
        protected void emitMap(Mistakes output, Map<?, ?> map) {
            for (Object object : map.values()) {
                emitObject(output, object);
            }
        }
        
        /**
         * Does nothing.
         * 
         * @param output
         *            The collection of mistakes.
         */
        @Override
        protected void emitScalar(Mistakes output, Object object) {
        }

        /**
         * Does nothing.
         * 
         * @param output
         *            The collection of mistakes.
         */
        @Override
        protected void emitNull(Mistakes output) {
        }
        
        /**
         * Validate the given object.
         * 
         * @param object
         *            The object to validate.
         * @param mistakes
         *            The collection of mistakes discovered.
         */
        public void validate(Object object, Mistakes mistakes) {
            emitObject(mistakes, object);
        }
    }
}
