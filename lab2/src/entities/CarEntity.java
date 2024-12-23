package entities;

import annotations.MaxValue;
import annotations.MinValue;
import annotations.NotNull;
import annotations.StringLength;

/**
 * Represents a car entity with fields for the brand, model, manufacturing year, and price.
 * The class uses annotations to enforce validation constraints on these fields.
 */
public class CarEntity {

    /**
     * The brand of the car.
     * Cannot be null and must have a length between 1 and 20 characters.
     */
    @NotNull
    @StringLength(min = 1, max = 20)
    private String brand;

    /**
     * The model of the car.
     * Cannot be null.
     */
    @NotNull
    private String model;

    /**
     * The manufacturing year of the car.
     * Must be between 2000 and 2024.
     */
    @MinValue(2000)
    @MaxValue(2024)
    private int manufacturedYear;

    /**
     * The price of the car.
     * Must be between 1000 and 20000.
     */
    @MinValue(1000)
    @MaxValue(20000)
    private int price;

    /**
     * Constructs a new CarEntity object.
     *
     * @param brand            the brand of the car
     * @param model            the model of the car
     * @param manufacturedYear the manufacturing year of the car
     * @param price            the price of the car
     */
    public CarEntity(String brand, String model, int manufacturedYear, int price) {
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufacturedYear;
        this.price = price;
    }
}
