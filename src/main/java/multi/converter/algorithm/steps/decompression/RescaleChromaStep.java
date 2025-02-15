package multi.converter.algorithm.steps.decompression;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.YUVData;

public class RescaleChromaStep extends AlgorithmStep<YUVData, YUVData> {
    private int upscaleFactorU;
    private int upscaleFactorV;

    public RescaleChromaStep(int upscaleFactorU, int upscaleFactorV) {
        this.upscaleFactorU = upscaleFactorU;
        this.upscaleFactorV = upscaleFactorV;
    }

    @Override
    public YUVData performAlgorithmStep(YUVData source) throws UnableToPerformStepException {
        byte[][] newChromaU = new byte[source.getHeightChromaU()*upscaleFactorU][source.getWidthChromaU()*upscaleFactorU];
        byte[][] newChromaV = new byte[source.getHeightChromaV()*upscaleFactorV][source.getWidthChromaV()*upscaleFactorV];

        Byte[][] oldChromaU = source.getChromaU();
        Byte[][] oldChromaV = source.getChromaV();

        for (int y = 0; y < newChromaU.length; y++) {
            for (int x = 0; x < newChromaU[0].length; x++) {
                newChromaU[y][x]  = oldChromaU[y / upscaleFactorU][x / upscaleFactorU];
            }
        }
        for (int y = 0; y < newChromaV.length; y++) {
            for (int x = 0; x < newChromaV[0].length; x++) {
                newChromaV[y][x]  = oldChromaV[y / upscaleFactorV][x / upscaleFactorV];
            }
        }

        return YUVData.fromArrays(
                source.getLumaPrimitive(),
                newChromaU,
                newChromaV
        );
    }
}
