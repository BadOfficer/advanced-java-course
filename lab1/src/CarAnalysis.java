import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Analyzes car prices to detect outliers based on interquartile range (IQR).
 */
public class CarAnalysis {
    private final List<Car> cars;

    /**
     * Constructor to initialize the car list.
     *
     * @param cars List of cars to analyze.
     */
    public CarAnalysis(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Perform analysis and print data points and outliers.
     */
    public void analyze() {
        List<Double> prices = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .toList();

        double Q1 = calculatePercentile(prices, 25);
        double Q3 = calculatePercentile(prices, 75);
        double IQR = Q3 - Q1;

        double lowerBound = Q1 - 1.5 * IQR;
        double upperBound = Q3 + 1.5 * IQR;

        long outliers = prices.stream().filter(price -> price < lowerBound || price > upperBound).count();
        long data = prices.size() - outliers;

        Map<String, Long> result = new HashMap<>();
        result.put("data", data);
        result.put("outliers", outliers);

        System.out.println("\n");
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Calculates a percentile value from a sorted list of values.
     *
     * @param values     The sorted list of values.
     * @param percentile The desired percentile (e.g., 25 for Q1).
     * @return The calculated percentile value.
     */
    private static double calculatePercentile(List<Double> values, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * values.size()) - 1;
        return values.get(index);
    }

}
