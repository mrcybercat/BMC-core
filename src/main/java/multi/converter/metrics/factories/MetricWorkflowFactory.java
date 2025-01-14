package multi.converter.metrics.factories;

import multi.converter.metrics.abstractions.*;

import java.util.List;

public abstract class MetricWorkflowFactory<T extends MetricTarget> {
    public abstract MetricWorkflow<T> createMetricWorkflow(MetricWorkflowType type);

    public MetricWorkflow<T> createMetricWorkflow(Metric<T>... metrics){
        return new MetricWorkflow<>(List.of(metrics));
    }

}
