package multi.converter.metrics.concrete.videoSeq;

import multi.converter.data.RGBSequence;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;

public class UIQISequentialMetric extends Metric<RGBSequence> {

    public UIQISequentialMetric() {
        this.type = MetricType.UIQI_SEQ;
    }

    @Override
    public double calculateMetric(RGBSequence original, RGBSequence altered) {
        double sum = 0;
        for(int i = 0; i < original.getDuration(); i++){
            sum += MatrixMetrics.uiqi(
                    original.getRGBData().get(i).getAveragedChannel(),
                    altered.getRGBData().get(i).getAveragedChannel()
            );
        }
        return sum / (double) original.getDuration();
    }
}
