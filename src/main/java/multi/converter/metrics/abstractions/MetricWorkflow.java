package multi.converter.metrics.abstractions;

import java.util.List;

public class MetricWorkflow<T extends MetricTarget>{
    private List<Metric<T>> metrics;

    public MetricWorkflow(List<Metric<T>> metrics) {
        this.metrics = metrics;
    }

    public MetricResult calculateMetrics(T original, T altered){
        MetricResult result = new MetricResult();
        for(Metric metric : this.metrics){
            result.addResult(
                    metric.getType(),
                    metric.calculateMetric(original, altered)
                    );
        }
        return result;
    }

    public void addStep(Metric<T> metric) {
        this.metrics.add(metric);
    }

    public void addSteps(Metric<T>... metrics) {
        this.metrics.addAll(List.of(metrics));
    }
}
