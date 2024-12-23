package validators;

import entities.UserEntity;
import exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that provides a method for validating user entities.
 * This class checks for specific validation rules applied to user attributes like name, password, and age.
 */
public class UserValidator {
    /**
     * Validates a user.
     * Checks:
     * - Whether the name is provided,
     * - Whether the password length is between 8 and 16 characters,
     * - Whether the user's age is between 1 and 100 years.
     *
     * @param user the {@link UserEntity} object to validate
     * @throws ValidationException if validation fails, an exception is thrown with error messages
     */
    public static void validateUser(UserEntity user) {
        List<String> errors = new ArrayList<>();

        if (user.getName() == null) {
            errors.add("Name is required");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
            errors.add("Password must be between 8 and 16 characters");
        }

        if (user.getAge() < 1 || user.getAge() > 100) {
            errors.add("Age must be between 1 and 100 characters");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Validation failed: " + String.join(", ", errors));
        }
    }
}
