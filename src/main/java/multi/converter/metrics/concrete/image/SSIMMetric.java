package multi.converter.metrics.concrete.image;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;

public class SSIMMetric extends Metric<RGBData> {

    public SSIMMetric() {
        this.type = MetricType.SSIM;
    }

    @Override
    public double calculateMetric(RGBData original, RGBData altered) {
        if(!original.hasSameDimensions(altered)){
            throw new UnableToPerformStepException("Unable to calculate metric for images with different dimensions");
        }
        return MatrixMetrics.ssim(original.getAveragedChannel(),  altered.getAveragedChannel());
    }
}
