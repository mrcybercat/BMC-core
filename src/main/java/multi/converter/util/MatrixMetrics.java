package multi.converter.util;

import java.util.Objects;

public final class MatrixMetrics {

    public static double gmsd(double[][] Y1, double[][] Y2){
        double T = 150d;
        final int downStep = 2;
        final double[][] dx = {
                {-0.3333, 0, 0.3333},
                {-0.3333, 0, 0.3333},
                {-0.3333, 0, 0.3333}
        };
        final double[][] dy = {
                { 0.3333,  0.3333,  0.3333},
                {      0,       0,       0},
                {-0.3333, -0.3333, -0.3333}
        };

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


    public static double uiqi(double[][] originalArray, double[][] alteredArray){
        double origMean = MatrixStatistics.mean(originalArray);
        double alteredMean = MatrixStatistics.mean(alteredArray);
        double originVar = MatrixStatistics.variance(originalArray);
        double alteredVar = MatrixStatistics.variance(alteredArray);
        double crossCovariance = MatrixStatistics.crossCovariance(originalArray, alteredArray);
        return (4 * crossCovariance * origMean * alteredMean) /
                ((originVar + alteredVar) * (origMean * origMean + alteredMean * alteredMean));
    }

    public static double ssim(double[][] originalArray, double[][] alteredArray){
        final double K1 = 0.01; // constants
        final double K2 = 0.03; // constants
        final int L = 255; // dynamic range of pixel values
        final double C1 = (K1 * L) * (K1 * L); // variables to stabilize division with weak denominator
        final double C2 = (K2 * L) * (K2 * L); // variables to stabilize division with weak denominator

        double origMean = MatrixStatistics.mean(originalArray);
        double alteredMean = MatrixStatistics.mean(alteredArray);
        double originVar = MatrixStatistics.variance(originalArray);
        double alteredVar = MatrixStatistics.variance(alteredArray);
        double crossCovariance = MatrixStatistics.crossCovariance(originalArray, alteredArray);

        double numerator = (2 * origMean * alteredMean + C1) * (2 * crossCovariance + C2);
        double denominator = (origMean * origMean + alteredMean * alteredMean + C1) * (originVar + alteredVar + C2);

        return numerator / denominator;
    }

}
