import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The main class to run the car analysis program.
 * It generates random car data, filters the cars based on user input, and displays the results.
 * Additionally, it calculates statistics and identifies outliers in the car prices.
 */
public class Main {

    /**
     * The entry point of the program.
     * It prompts the user for input parameters, generates a list of random cars,
     * filters them based on the user-defined criteria, and displays detailed information
     * about the filtered cars. It also computes and displays car statistics and outliers.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Prompt the user for input parameters
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of skipped elements (N): ");
        int n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter car brand to skip: ");
        String carBrand = scanner.nextLine();

        System.out.print("Enter the first number of full month since manufacturing: ");
        int firstFullMonth = scanner.nextInt();

        System.out.print("Enter the last number of full month since manufacturing: ");
        int lastFullMonth = scanner.nextInt();
        scanner.close();

        // Generate a list of random cars, with some skipped based on user input
        List<Car> cars = CarGenerator.generateCars().gather(new CarGatherer(n, carBrand, 500)).toList();

        // Filter cars based on their manufacturing date (months between manufacture date and current date)
        Map<String, List<Car>> filterCars = cars.stream()
                .filter(car -> {
                    int currentDate = (int) ChronoUnit.MONTHS.between(car.getManufactureDate(), LocalDate.now());
                    return firstFullMonth < currentDate && currentDate < lastFullMonth;
                })
                .collect(Collectors.groupingBy(Car::getCarClass));

        // Display the filtered cars grouped by car class
        filterCars.forEach((carClass, carList) -> {
            System.out.println("\nCar Class: " + carClass);
            carList.forEach(car -> {
                System.out.printf("\tCar: %s - %s, year - %d, price - %.2f UAH, class - %s%n",
                        car.getBrand(), car.getModel(),
                        car.getManufactureDate().getYear(),
                        car.getPrice(), car.getCarClass());
            });
        });

        // Calculate and display car statistics (min, max, average price, standard deviation)
        CarStatistics statistics = cars.stream().collect(new CarStatisticsCollector());
        statistics.getStats();

        // Analyze and display the number of data points and outliers in car prices
        CarAnalysis carAnalysis = new CarAnalysis(cars);
        carAnalysis.analyze();
    }
}
