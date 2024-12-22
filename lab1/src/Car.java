import java.time.LocalDate;

/**
 * Represents a car with details such as brand, model, manufacture date, class, and price.
 */
public class Car {
    private final String brand;
    private final String model;
    private final LocalDate manufactureDate;
    private final String carClass;
    private final double price;

    /**
     * Constructor to initialize a car.
     *
     * @param brand           The car brand.
     * @param model           The car model.
     * @param manufactureDate The manufacture date.
     * @param carClass        The class of the car.
     * @param price           The price of the car.
     */
    public Car(String brand, String model, LocalDate manufactureDate, String carClass, double price) {
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.carClass = carClass;
        this.price = price;
    }

    /**
     * Gets the brand of the car.
     *
     * @return the brand of the car.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the model of the car.
     *
     * @return the model of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the class of the car.
     *
     * @return the class of the car.
     */
    public String getCarClass() {
        return carClass;
    }

    /**
     * Gets the price of the car.
     *
     * @return the price of the car.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the manufacture date of the car.
     *
     * @return the manufacture date of the car.
     */
    public LocalDate getManufactureDate() {
        return manufactureDate;
    }
}
