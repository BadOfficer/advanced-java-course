import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * Custom collector that filters and collects cars based on specific criteria.
 */
public class CarGatherer implements Gatherer<Car, List<Car>, Car> {

    private final int skipCount;
    private final String brandToSkip;
    private final int limit;
    private int count;

    /**
     * Constructs a CarGatherer with filtering and limiting conditions.
     *
     * @param skipCount   Number of cars of a specific brand to skip.
     * @param brandToSkip The brand of cars to skip.
     * @param limit       Maximum number of cars to collect.
     */
    public CarGatherer(int skipCount, String brandToSkip, int limit) {
        this.skipCount = skipCount;
        this.brandToSkip = brandToSkip;
        this.limit = limit;
        this.count = 0;
    }


    @Override
    public Supplier<List<Car>> initializer() {
        return ArrayList::new;
    }

    @Override
    public Integrator<List<Car>, Car, Car> integrator() {
        return (state, element, downstream) -> {
            boolean isSkip = element.getBrand().equalsIgnoreCase(brandToSkip) && state.size() < skipCount;
            if (isSkip) {
                state.add(element);
                return true;
            } else if (count < limit) {
                count++;
                return downstream.push(element);
            }
            return false;
        };
    }

    @Override
    public BinaryOperator<List<Car>> combiner() {
        return Gatherer.super.combiner();
    }

    @Override
    public BiConsumer<List<Car>, Downstream<? super Car>> finisher() {
        return Gatherer.super.finisher();
    }
}
