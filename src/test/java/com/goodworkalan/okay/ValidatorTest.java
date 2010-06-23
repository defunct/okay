package com.goodworkalan.okay;

import org.testng.annotations.Test;

import com.goodworkalan.okay.Mistakes;
import com.goodworkalan.okay.ObjectValidator;
import com.goodworkalan.okay.Validator;

/**
 * Unit tests for the {@link Validator} class.
 *
 * @author Alan Gutierrez
 */
public class ValidatorTest {
    /** Test basic validation. */
    @Test
    public void test() {
        Validator validator = new Validator();
        validator.add(Person.class, new ObjectValidator<Person>() {
            public void validate(Person person, Mistakes mistakes) {
                if (person.lastName.equals("Hatfield")) {
                    mistakes.mistake(person, "lastName", "not.mccoy", person.firstName, person.lastName);
                }
            }
        });
    }
}
