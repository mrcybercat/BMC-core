package multi.converter.metrics.concrete.image;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixStatistics;

public class PNSRMetric extends Metric<RGBData> {

    public PNSRMetric() {
        this.type = MetricType.PSNR;
    }

    @Override
    public  double calculateMetric(RGBData original, RGBData altered) {
        if(!original.hasSameDimensions(altered)){
            throw new UnableToPerformStepException("Unable to calculate metric for images with different dimensions");
        }
        return MatrixStatistics.peakNoiseToSignalRation(original.getAveragedChannel(),  altered.getAveragedChannel());
    }
}
