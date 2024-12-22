import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * A custom collector that calculates various statistics for car prices such as minimum, maximum, average price, and standard deviation.
 */
public class CarStatisticsCollector implements Collector<Car, List<Double>, CarStatistics> {

    @Override
    public Supplier<List<Double>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Double>, Car> accumulator() {
        return (list, car) -> list.add(car.getPrice());
    }

    @Override
    public BinaryOperator<List<Double>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<Double>, CarStatistics> finisher() {
        return listOfPrices -> {
            double minPrice = listOfPrices.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
            double maxPrice = listOfPrices.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);

            double averagePrice = listOfPrices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double variance = listOfPrices.stream().mapToDouble(i -> Math.pow(i - averagePrice, 2)).average().orElse(0.0);
            double standardDeviation = Math.sqrt(variance);

            return new CarStatistics(minPrice, maxPrice, averagePrice, standardDeviation);
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
