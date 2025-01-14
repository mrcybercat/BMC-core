package multi.converter.metrics.concrete.video3d;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.CuboidData;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.Matrix3DStatistics;
import multi.converter.util.MatrixStatistics;

public class MSE3DMetric extends Metric<CuboidData> {

    public MSE3DMetric() {
        this.type = MetricType.MSE_3D;
    }

    @Override
    public double calculateMetric(CuboidData original, CuboidData altered) {
        return Matrix3DStatistics.meanSquaredError(original.get3DMatrix(), altered.get3DMatrix());
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
