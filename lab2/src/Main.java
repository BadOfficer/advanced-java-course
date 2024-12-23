import entities.CarEntity;
import entities.ProductEntity;
import entities.UserEntity;
import validators.CustomValidator;
import validators.UserValidator;

/**
 * Main entry point for testing validation on different entities (CarEntity, ProductEntity, and UserEntity).
 * The program demonstrates validation using reflection-based custom annotations and manually written validation for users.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        CarEntity car = new CarEntity("BMW", "X5", 2015, 15000);
        UserEntity user = new UserEntity("Taras", "Test12345678", 19);
        ProductEntity product = new ProductEntity("Test product", "Test description for test product", 140);

        CustomValidator.validate(car);
        CustomValidator.validate(user);

        long startTime = System.nanoTime();
        CustomValidator.validate(product);
        long endTime = System.nanoTime();
        System.out.println("Execution time with reflection: " + (endTime - startTime) + " ns");

        long startTimeWithoutReflection = System.nanoTime();
        UserValidator.validateUser(user);
        long endTimeWithoutReflection = System.nanoTime();
        System.out.println("Execution time without reflection: " + (endTimeWithoutReflection - startTimeWithoutReflection) + " ns");
    }
}