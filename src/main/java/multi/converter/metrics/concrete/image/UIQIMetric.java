package multi.converter.metrics.concrete.image;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;
import multi.converter.util.MatrixStatistics;

public class UIQIMetric extends Metric<RGBData> {

    public UIQIMetric() {
        this.type = MetricType.UIQI;
    }

    @Override
    public double calculateMetric(RGBData original, RGBData altered) {
        if(!original.hasSameDimensions(altered)){
            throw new UnableToPerformStepException("Unable to calculate metric for images with different dimensions");
        }
        return MatrixMetrics.uiqi(original.getAveragedChannel(), altered.getAveragedChannel());
    }
}
