package multi.converter.metrics.concrete.videoSeq;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.RGBSequence;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;
import multi.converter.util.MatrixStatistics;

import java.util.Objects;

public class GMSDSequentialMetric extends Metric<RGBSequence> {

    public GMSDSequentialMetric() {
        this.type = MetricType.GMSD_SEQ;
    }

    @Override
    public double calculateMetric(RGBSequence original, RGBSequence altered) {
        double sum = 0;
        for(int i = 0; i < original.getDuration(); i++){
            sum += MatrixMetrics.gmsd(
                    original.getRGBData().get(i).getAveragedChannel(),
                    altered.getRGBData().get(i).getAveragedChannel()
            );
        }
        return sum / (double) original.getDuration();
    }
}
