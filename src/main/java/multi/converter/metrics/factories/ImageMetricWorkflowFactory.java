package multi.converter.metrics.factories;

import multi.converter.data.color.RGBData;
import multi.converter.metrics.concrete.image.*;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;

import java.util.ArrayList;
import java.util.List;


public class ImageMetricWorkflowFactory extends MetricWorkflowFactory<RGBData>{
    @Override
    public MetricWorkflow<RGBData> createMetricWorkflow(MetricWorkflowType type) {
        List<Metric<RGBData>> metrics = new ArrayList<>();

        switch (type){
            case LIGHT_IMAGE -> {
                metrics.add(new MSEMetric());
                metrics.add(new PNSRMetric());
            }
            case MODERN_IMAGE -> {
                metrics.add(new GMSDMetric());
                metrics.add(new PNSRMetric());
                metrics.add(new UIQIMetric());
            }
            case COMPREHENSIVE_IMAGE -> {
                metrics.add(new MSEMetric());
                metrics.add(new PNSRMetric());
                metrics.add(new GMSDMetric());
                metrics.add(new SSIMMetric());
                metrics.add(new UIQIMetric());
            }
        }
        return new MetricWorkflow<>(metrics) ;
    }
}
