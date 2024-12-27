package org.ajc;

import org.ajc.entities.CarEntity;
import org.ajc.entities.ProductEntity;
import org.ajc.entities.UserEntity;
import org.ajc.validators.CustomValidator;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        CarEntity car = new CarEntity("BMW", "X5", 2015, 15000);
        UserEntity user = new UserEntity("Taras", "Test12345678", 19);
        ProductEntity product = new ProductEntity("Test product", "Test description for test product", 140);

        CustomValidator.validate(car);
        CustomValidator.validate(user);
        CustomValidator.validate(product);
    }
}
