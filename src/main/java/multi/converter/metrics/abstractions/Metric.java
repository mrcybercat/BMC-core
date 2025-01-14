package multi.converter.metrics.abstractions;

public abstract class Metric<T extends MetricTarget> {
    protected MetricType type;

    public abstract double calculateMetric(T original, T altered);

    public MetricType getType() {
        return type;
    }
}
