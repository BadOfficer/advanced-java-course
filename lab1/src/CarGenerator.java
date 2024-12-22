import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Generates random car objects with various attributes.
 */
public class CarGenerator {
    private static final Map<String, String[]> BRAND_MODELS = new HashMap<>();
    private static final String[] CLASSES = {"A", "B", "C", "D"};
    private static final Random RANDOM = new Random();

    static {
        BRAND_MODELS.put("Mercedes-Benz", new String[]{"A-Class", "C-Class", "E-Class", "S-Class", "GLE"});
        BRAND_MODELS.put("VW", new String[]{"Golf", "Passat", "Tiguan", "Touareg", "Polo"});
        BRAND_MODELS.put("BMW", new String[]{"320i", "M5", "X5", "X7", "Z4"});
        BRAND_MODELS.put("Audi", new String[]{"A4", "Q5", "R8", "A6", "Q7"});
        BRAND_MODELS.put("Porsche", new String[]{"911 GT3", "Cayenne", "Taycan", "Panamera"});
        BRAND_MODELS.put("Opel", new String[]{"Astra", "Vectra", "Corsa", "Insignia"});
    }

    /**
     * Generates an infinite stream of random cars.
     *
     * @return A stream of random Car objects.
     */
    public static Stream<Car> generateCars() {
        return Stream.generate(() -> {
            String brand = getRandomBrand();
            String carClass = CLASSES[RANDOM.nextInt(CLASSES.length)];
            return new Car(
                    brand,
                    getRandomModel(brand),
                    generateRandomDate(),
                    carClass,
                    generatePriceForClass(carClass)
            );
        });
    }

    /**
     * Selects a random brand from the available brands.
     *
     * @return A randomly selected brand name.
     */
    private static String getRandomBrand() {
        List<String> brands = new ArrayList<>(BRAND_MODELS.keySet());
        return brands.get(RANDOM.nextInt(brands.size()));
    }

    /**
     * Selects a random model for a given brand.
     *
     * @param brand The brand for which a random model will be selected.
     * @return A randomly selected model for the given brand.
     */
    private static String getRandomModel(String brand) {
        String[] models = BRAND_MODELS.get(brand);
        return models[RANDOM.nextInt(models.length)];
    }

    /**
     * Generates a random price based on the class of the car.
     *
     * @param carClass The class of the car (A, B, C, or D).
     * @return A randomly generated price for the car based on its class.
     */
    private static double generatePriceForClass(String carClass) {
        return switch (carClass) {
            case "A" -> Math.floor(2_000_000 + (RANDOM.nextDouble() * 3_000_000));
            case "B" -> Math.floor(800_000 + (RANDOM.nextDouble() * 1_200_000));
            case "C" -> Math.floor(300_000 + (RANDOM.nextDouble() * 500_000));
            case "D" -> Math.floor(100_000 + (RANDOM.nextDouble() * 200_000));
            default -> 500_000;
        };
    }

    /**
     * Generates a random manufacturing date for the car.
     *
     * @return A randomly generated {@link LocalDate} object representing the manufacturing date.
     */
    private static LocalDate generateRandomDate() {
        int year = RANDOM.nextInt(2005, 2025);
        int dayOfYear = RANDOM.nextInt(1, LocalDate.of(year, 12, 31).lengthOfYear() + 1);
        return LocalDate.ofYearDay(year, dayOfYear);
    }
}
