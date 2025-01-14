package multi.converter.metrics.concrete.image;

import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.RGBData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.MatrixMetrics;
import multi.converter.util.MatrixStatistics;

import java.util.Objects;

public class GMSDMetric extends Metric<RGBData> {

    public GMSDMetric() {
        this.type = MetricType.GMSD;
    }


    @Override
    public double calculateMetric(RGBData original, RGBData altered) {
        if(!original.hasSameDimensions(altered)){
            throw new UnableToPerformStepException("Unable to calculate metric for images with different dimensions");
        }
        return MatrixMetrics.gmsd(original.getAveragedChannel(), altered.getAveragedChannel());
    }


    private static final double T = 150d;
    private static final int downStep = 2;
    private static final double[][] dx = {
            {-0.3333, 0, 0.3333},
            {-0.3333, 0, 0.3333},
            {-0.3333, 0, 0.3333}
    };
    private static final double[][] dy = {
            {0.3333, 0.3333, 0.3333},
            {0, 0, 0},
            {-0.3333, -0.3333, -0.3333}
    };


    public double calculateMetric(double[][] Y1, double[][] Y2) {
        double[][] aveKernel = MatrixStatistics.averagingFilter(2);
        double[][] aveY1 = MatrixStatistics.convolve(Y1, aveKernel);
        double[][] aveY2 = MatrixStatistics.convolve(Y2, aveKernel);

        Y1 = MatrixStatistics.downScale(aveY1, downStep);
        Y2 = MatrixStatistics.downScale(aveY2, downStep);

        double[][] IxY1 = MatrixStatistics.convolve(Y1, dx);
        double[][] IyY1 = MatrixStatistics.convolve(Y1, dy);
        double[][] gradientMap1 = MatrixStatistics.sqrt(
                Objects.requireNonNull(MatrixStatistics.sum(
                        MatrixStatistics.pow(IxY1, 2),
                        MatrixStatistics.pow(IyY1, 2)
                ))
        );

        double[][] IxY2 = MatrixStatistics.convolve(Y2, dx);
        double[][] IyY2 = MatrixStatistics.convolve(Y2, dy);
        double[][] gradientMap2 = MatrixStatistics.sqrt(
                Objects.requireNonNull(MatrixStatistics.sum(
                        MatrixStatistics.pow(IxY2, 2),
                        MatrixStatistics.pow(IyY2, 2)
                ))
        );

        double[][] numerator = MatrixStatistics.addConst(
                MatrixStatistics.multiplyConst(
                        Objects.requireNonNull(MatrixStatistics.multiplyPlain(
                                gradientMap1,
                                gradientMap2
                        )), 2
                ), T
        );
        double[][] denominator = MatrixStatistics.addConst(
                Objects.requireNonNull(MatrixStatistics.sum(
                        MatrixStatistics.pow(gradientMap1, 2),
                        MatrixStatistics.pow(gradientMap2, 2)
                )), T
        );


        double[][] qualityMap = MatrixStatistics.divide(numerator, denominator);
        return MatrixStatistics.standardDeviation(qualityMap);
    }
}
