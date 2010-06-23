package com.goodworkalan.okay.annotations;

import com.goodworkalan.stringbeans.validation.annotations.ValidatePresenceOf;
import com.goodworkalan.stringbeans.validation.annotations.ValidateRange;

/**
 * An example person bean.
 *
 * @author Alan Gutierrez
 */
public class Person {
    /**  The first name. */
    @ValidatePresenceOf
    public String firstName;
    
    /** The last name. */
    @ValidatePresenceOf
    public String lastName;
    
    /** The age. */
    @ValidatePresenceOf @ValidateRange(min = 0, max = 150)
    public int age;
}
