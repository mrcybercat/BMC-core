package multi.converter.metrics.concrete.videoSeq;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.RGBSequence;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;
import multi.converter.util.MatrixStatistics;

public class MSESequentialMetric extends Metric<RGBSequence> {

    public MSESequentialMetric() {
        this.type = MetricType.MSE_SEQ;
    }

    @Override
    public double calculateMetric(RGBSequence original, RGBSequence altered) {
        double sum = 0;
        for(int i = 0; i < original.getDuration(); i++){
            sum += MatrixStatistics.meanSquaredError(
                    original.getRGBData().get(i).getAveragedChannel(),
                    altered.getRGBData().get(i).getAveragedChannel()
            );
        }
        return sum / (double) original.getDuration();
    }
}
        /*

        double mse = 0;
        for (int y = 0; y < original.getFirstChannelHeight(); y++) {
            for (int x = 0; x < original.getFirstChannelWidth(); x++) {
                mse +=  Math.pow((original.getChannelAverageAtXY(x, y) - altered.getChannelAverageAtXY(x, y)), 2);
            }
        }
        mse = mse / (original.getFirstChannelHeight() * original.getFirstChannelWidth());
        return mse;
         */
