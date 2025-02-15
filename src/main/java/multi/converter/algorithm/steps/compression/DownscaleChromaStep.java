package multi.converter.algorithm.steps.compression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.YUVData;

public class DownscaleChromaStep  extends AlgorithmStep<YUVData, YUVData> {
    private int downscaleFactorU;
    private int downscaleFactorV;

    public DownscaleChromaStep(int downscaleFactorU, int downscaleFactorV) {
        this.downscaleFactorU = downscaleFactorU;
        this.downscaleFactorV = downscaleFactorV;
    }

    @Override
    public YUVData performAlgorithmStep(YUVData source) throws UnableToPerformStepException {
        byte[][] newChromaU = new byte[source.getHeightChromaU()/downscaleFactorU][source.getWidthChromaU()/downscaleFactorU];
        byte[][] newChromaV = new byte[source.getHeightChromaV()/downscaleFactorV][source.getWidthChromaV()/downscaleFactorV];

        Byte[][] oldChromaU = source.getChromaU();
        Byte[][] oldChromaV = source.getChromaV();

        for (int y = 0; y < newChromaU.length; y++) {
            for (int x = 0; x < newChromaU[0].length; x++) {
                newChromaU[y][x]  = oldChromaU[y * downscaleFactorU][x * downscaleFactorU];
            }
        }
        for (int y = 0; y < newChromaV.length; y++) {
            for (int x = 0; x < newChromaV[0].length; x++) {
                newChromaV[y][x]  = oldChromaV[y * downscaleFactorV][x * downscaleFactorV];
            }
        }

        return YUVData.fromArrays(
                source.getLumaPrimitive(),
                newChromaU,
                newChromaV
        );
    }
}
