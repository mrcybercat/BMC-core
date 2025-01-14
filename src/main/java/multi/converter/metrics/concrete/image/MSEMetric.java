package multi.converter.metrics.concrete.image;

import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixStatistics;

public class MSEMetric extends Metric<RGBData> {

    public MSEMetric() {
        this.type = MetricType.MSE;
    }

    @Override
    public double calculateMetric(RGBData original, RGBData altered) {
        if(!original.hasSameDimensions(altered)){
            throw new UnableToPerformStepException("Unable to calculate metric for images with different dimensions");
        }
        return MatrixStatistics.meanSquaredError(original.getAveragedChannel(), altered.getAveragedChannel());
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
