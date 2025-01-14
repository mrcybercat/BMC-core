package multi.converter.metrics.factories;

import multi.converter.data.CuboidData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricWorkflow;
import multi.converter.metrics.abstractions.MetricWorkflowType;
import multi.converter.metrics.concrete.video3d.*;

import java.util.ArrayList;
import java.util.List;

public class Metric3DWorkflowFactory extends MetricWorkflowFactory<CuboidData>{
    @Override
    public MetricWorkflow<CuboidData> createMetricWorkflow(MetricWorkflowType type) {
        List<Metric<CuboidData>> metrics = new ArrayList<>();

        switch (type){
            case LIGHT_IMAGE -> {
                metrics.add(new MSE3DMetric());
                metrics.add(new PNSR3DMetric());
            }
            case MODERN_IMAGE -> {
                metrics.add(new GMSD3DMetric());
                metrics.add(new SSIM3DMetric());
                metrics.add(new UIQI3DMetric());
            }
            case COMPREHENSIVE_IMAGE -> {
                metrics.add(new MSE3DMetric());
                metrics.add(new PNSR3DMetric());
                metrics.add(new GMSD3DMetric());
                metrics.add(new SSIM3DMetric());
                metrics.add(new UIQI3DMetric());
            }
        }
        return new MetricWorkflow<>(metrics) ;

    }
}
