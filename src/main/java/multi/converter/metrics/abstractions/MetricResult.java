package multi.converter.metrics.abstractions;

import java.util.HashMap;
import java.util.Map;

public class MetricResult {
    private Map<MetricType, Double> results;

    public MetricResult() {
        this.results = new HashMap<MetricType, Double>();
    }

    public void addResult(MetricType metricType, double value) {
         this.results.put(metricType, value);
    }


    @Override
    public String toString() {
        return "MetricResult{" +
                "results=" + results +
                '}';
    }
}
