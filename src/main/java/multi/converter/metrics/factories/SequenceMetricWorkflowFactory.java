package multi.converter.metrics.factories;

import multi.converter.data.RGBSequence;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;
import multi.converter.metrics.concrete.image.MSEMetric;
import multi.converter.metrics.concrete.image.PNSRMetric;
import multi.converter.metrics.concrete.videoSeq.*;

import java.util.ArrayList;
import java.util.List;

public class SequenceMetricWorkflowFactory extends MetricWorkflowFactory<RGBSequence>{
    @Override
    public MetricWorkflow<RGBSequence> createMetricWorkflow(MetricWorkflowType type) {
        List<Metric<RGBSequence>> metrics = new ArrayList<>();

        switch (type){
            case LIGHT_IMAGE -> {
                metrics.add(new MSESequentialMetric());
                metrics.add(new PNSRSequentialMetric());
            }
            case MODERN_IMAGE -> {
                metrics.add(new GMSDSequentialMetric());
                metrics.add(new SSIMSequentialMetric());
                metrics.add(new UIQISequentialMetric());
            }
            case COMPREHENSIVE_IMAGE -> {
                metrics.add(new MSESequentialMetric());
                metrics.add(new PNSRSequentialMetric());
                metrics.add(new GMSDSequentialMetric());
                metrics.add(new SSIMSequentialMetric());
                metrics.add(new UIQISequentialMetric());
            }
        }
        return new MetricWorkflow<>(metrics) ;
    }
}
