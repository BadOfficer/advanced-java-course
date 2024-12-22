/**
 * Contains statistics for a collection of cars such as minimum, maximum, average price, and standard deviation.
 */

class CarStatistics {
    private final double minPrice;
    private final double maxPrice;
    private final double avgPrice;
    private final double stdDeviation;

    /**
     * Constructs a CarStatistics object with the specified statistical values.
     *
     * @param minPrice     The minimum price among the cars.
     * @param maxPrice     The maximum price among the cars.
     * @param avgPrice     The average price of the cars.
     * @param stdDeviation The standard deviation of the car prices.
     */
    public CarStatistics(double minPrice, double maxPrice, double avgPrice, double stdDeviation) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
        this.stdDeviation = stdDeviation;
    }

    /**
     * Prints the car statistics.
     */
    public void getStats() {
        System.out.printf("\nMin price: %.2f UAH", minPrice);
        System.out.printf("\nMax price: %.2f UAH", maxPrice);
        System.out.printf("\nAverage price: %.2f UAH", avgPrice);
        System.out.printf("\nStandard Deviation price: %.2f UAH", stdDeviation);
    }
}