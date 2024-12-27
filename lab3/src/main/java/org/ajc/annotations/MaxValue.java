package org.ajc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to enforce a maximum value constraint on an integer field.
 * It is applied to a field to specify that the value of the field must not exceed the specified value.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface MaxValue {

    /**
     * The maximum allowed value for the field.
     * @return the maximum value for the field
     */
    int value();
}
