package multi.converter.metrics.concrete.video3d;

import multi.converter.data.CuboidData;
import multi.converter.metrics.abstractions.Metric;
import multi.converter.metrics.abstractions.MetricType;
import multi.converter.util.Matrix3DStatistics;

public class PNSR3DMetric extends Metric<CuboidData> {

    public PNSR3DMetric() {
        this.type = MetricType.PSNR_3D;
    }

    @Override
    public  double calculateMetric(CuboidData original, CuboidData altered) {
        return Matrix3DStatistics.peakNoiseToSignalRation(original.get3DMatrix(), altered.get3DMatrix());
    }
}
