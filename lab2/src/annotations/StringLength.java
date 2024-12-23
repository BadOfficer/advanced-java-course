package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to enforce a length constraint on a string field.
 * It is applied to a field to specify the minimum and maximum length of the field's value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringLength {
    /**
     * The minimum allowed length for the string field.
     * Default is 0.
     * @return the minimum length for the string field
     */
    int min() default 0;

    /**
     * The maximum allowed length for the string field.
     * Default is Integer.MAX_VALUE.
     * @return the maximum length for the string field
     */
    int max() default Integer.MAX_VALUE;
}
