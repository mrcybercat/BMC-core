package multi.converter.metrics.concrete.video3d;

import multi.converter.data.CuboidData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.Matrix3DStatistics;

import java.util.Objects;

public class GMSD3DMetric extends Metric<CuboidData> {
    
    private static final double T = 150d;
    private static final int downStep = 2;
    private static final double[][][] dx = {
            {
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333}
            },
            {
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333}
            },
            {
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333},
                    {-0.3333, 0, 0.3333}
            }
    };

    private static final double[][][] dy = {
            {
                    {0.3333, 0.3333, 0.3333},
                    {0, 0, 0},
                    {-0.3333, -0.3333, -0.3333}
            },
            {
                    {0.3333, 0.3333, 0.3333},
                    {0, 0, 0},
                    {-0.3333, -0.3333, -0.3333}
            },
            {
                    {0.3333, 0.3333, 0.3333},
                    {0, 0, 0},
                    {-0.3333, -0.3333, -0.3333}
            }
    };
    
    
    public GMSD3DMetric() {
        this.type = MetricType.GMSD_3D;
    }


    @Override
    public double calculateMetric(CuboidData original, CuboidData altered) {
        double[][][] Y1 = original.get3DMatrix();
        double[][][] Y2 = altered.get3DMatrix();
        
        double[][][] aveKernel = Matrix3DStatistics.averagingFilter(2);
        double[][][] aveY1 = Matrix3DStatistics.convolve(Y1, aveKernel);
        double[][][] aveY2 = Matrix3DStatistics.convolve(Y2, aveKernel);

        Y1 = Matrix3DStatistics.downScale(aveY1, downStep);
        Y2 = Matrix3DStatistics.downScale(aveY2, downStep);

        double[][][] IxY1 = Matrix3DStatistics.convolve(Y1, dx);
        double[][][] IyY1 = Matrix3DStatistics.convolve(Y1, dy);
        double[][][] gradientMap1 = Matrix3DStatistics.sqrt(
                Objects.requireNonNull(Matrix3DStatistics.sum(
                        Matrix3DStatistics.pow(IxY1, 2),
                        Matrix3DStatistics.pow(IyY1, 2)
                ))
        );

        double[][][] IxY2 = Matrix3DStatistics.convolve(Y2, dx);
        double[][][] IyY2 = Matrix3DStatistics.convolve(Y2, dy);
        double[][][] gradientMap2 = Matrix3DStatistics.sqrt(
                Objects.requireNonNull(Matrix3DStatistics.sum(
                        Matrix3DStatistics.pow(IxY2, 2),
                        Matrix3DStatistics.pow(IyY2, 2)
                ))
        );

        double[][][] numerator = Matrix3DStatistics.addConst(
                Matrix3DStatistics.multiplyConst(
                        Objects.requireNonNull(Matrix3DStatistics.multiplyPlain(
                                gradientMap1,
                                gradientMap2
                        )), 2
                ), T
        );

        double[][][] denominator = Matrix3DStatistics.addConst(
                Objects.requireNonNull(Matrix3DStatistics.sum(
                        Matrix3DStatistics.pow(gradientMap1, 2),
                        Matrix3DStatistics.pow(gradientMap2, 2)
                )), T
        );

        double[][][] qualityMap = Matrix3DStatistics.divide(numerator, denominator);
        return Matrix3DStatistics.standardDeviation(qualityMap);
    }
}
