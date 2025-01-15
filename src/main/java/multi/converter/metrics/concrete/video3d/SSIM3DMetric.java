package multi.converter.metrics.concrete.video3d;

import multi.converter.data.CuboidData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.Matrix3DStatistics;

public class SSIM3DMetric extends Metric<CuboidData> {

    final double K1 = 0.01; // constants
    final double K2 = 0.03; // constants
    final int L = 255; // dynamic range of pixel values
    final double C1 = (K1 * L) * (K1 * L); // variables to stabilize division with weak denominator
    final double C2 = (K2 * L) * (K2 * L); // variables to stabilize division with weak denominator


    public SSIM3DMetric() {
        this.type = MetricType.SSIM_3D;
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

        double numerator = (2 * origMean * alteredMean + C1) * (2 * crossCovariance + C2);
        double denominator = (origMean * origMean + alteredMean * alteredMean + C1) * (originVar + alteredVar + C2);

        return numerator / denominator;
    }
}
