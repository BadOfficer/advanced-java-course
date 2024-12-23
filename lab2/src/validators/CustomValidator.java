package validators;

import annotations.MaxValue;
import annotations.MinValue;
import annotations.NotNull;
import annotations.StringLength;
import exceptions.ValidationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that provides a method for validating objects based on custom annotations.
 * This class uses reflection to inspect the annotations applied to the fields of an object.
 * If any validation constraints are violated, a ValidationException is thrown.
 */
public class CustomValidator {

    /**
     * Validates an object by checking its fields for any validation annotations and ensuring they are satisfied.
     *
     * @param object the object to validate
     * @throws IllegalAccessException if there is an issue accessing the field's value
     * @throws ValidationException    if any validation constraint is violated
     */
    public static void validate(Object object) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);

            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                errors.add("Field " + field.getName() + " is required");
            }

            if (field.isAnnotationPresent(StringLength.class)) {
                if (value instanceof String stringValue) {
                    int length = stringValue.length();
                    StringLength annotation = field.getAnnotation(StringLength.class);

                    if (length < annotation.min()) {
                        errors.add("Field " + field.getName() + " is too short");
                    }

                    if (length > annotation.max()) {
                        errors.add("Field " + field.getName() + " is too long");
                    }
                } else {
                    errors.add("Invalid annotation usage on " + field.getName());
                }
            }

            if (field.isAnnotationPresent(MinValue.class)) {
                MinValue annotation = field.getAnnotation(MinValue.class);
                if (value instanceof Integer fieldValue) {
                    if (fieldValue < annotation.value()) {
                        errors.add(field.getName() + " must be at least " + annotation.value());
                    }
                } else {
                    errors.add("Invalid annotation usage on " + field.getName());
                }
            }

            if (field.isAnnotationPresent(MaxValue.class)) {
                MaxValue annotation = field.getAnnotation(MaxValue.class);

                if (value instanceof Integer fieldValue) {
                    if (fieldValue > annotation.value()) {
                        errors.add(field.getName() + " must be less than or equal to " + annotation.value());
                    }
                } else {
                    errors.add("Invalid annotation usage on " + field.getName());
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation failed: " + String.join(", ", errors));
        }
    }
}
