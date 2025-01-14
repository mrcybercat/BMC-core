package multi.converter.metrics.concrete.videoSeq;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.RGBSequence;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixStatistics;

public class PNSRSequentialMetric extends Metric<RGBSequence> {

    public PNSRSequentialMetric() {
        this.type = MetricType.PSNR_SEQ;
    }

    @Override
    public  double calculateMetric(RGBSequence original, RGBSequence altered) {
        double sum = 0;
        for(int i = 0; i < original.getDuration(); i++){
            sum += MatrixStatistics.peakNoiseToSignalRation(
                    original.getRGBData().get(i).getAveragedChannel(),
                    altered.getRGBData().get(i).getAveragedChannel()
            );
        }
        return sum / (double) original.getDuration();
    }
}
