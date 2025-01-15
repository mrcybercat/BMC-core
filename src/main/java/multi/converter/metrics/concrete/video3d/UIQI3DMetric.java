package multi.converter.metrics.concrete.video3d;

import multi.converter.data.CuboidData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.Matrix3DStatistics;

public class UIQI3DMetric extends Metric<CuboidData> {

    public UIQI3DMetric() {
        this.type = MetricType.UIQI_3D;
    }

    @Override
    public double calculateMetric(CuboidData original, CuboidData altered) {
        double[][][] originalArray = original.get3DMatrix();
        double[][][] alteredArray = altered.get3DMatrix();
        
        double origMean = Matrix3DStatistics.mean(originalArray);
        double alteredMean = Matrix3DStatistics.mean(alteredArray);
        double originVar = Matrix3DStatistics.variance(originalArray);
        double alteredVar = Matrix3DStatistics.variance(alteredArray);
        double crossCovariance = Matrix3DStatistics.crossCovariance(originalArray, alteredArray);
        return (4 * crossCovariance * origMean * alteredMean) /
                ((originVar + alteredVar) * (origMean * origMean + alteredMean * alteredMean));
    }
}
