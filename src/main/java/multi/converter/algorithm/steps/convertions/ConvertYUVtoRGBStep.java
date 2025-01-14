package multi.converter.algorithm.steps.convertions;

import multi.converter.data.color.RGBData;
import multi.converter.data.color.YUVData;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;

public class ConvertYUVtoRGBStep extends AlgorithmStep<RGBData, YUVData> {

    // TODO: Fix value flipping
    @Override
    public RGBData performAlgorithmStep(YUVData source) {
        if(source.getHeight() != source.getHeightChromaU() || source.getWidth() != source.getWidthChromaV()){
            throw new UnableToPerformStepException("Cannot perform conversion if U or V channels were down scaled by unspecified factor");
        }

        byte[][] red    = new byte[source.getHeight()][source.getWidth()];
        byte[][] green  = new byte[source.getHeight()][source.getWidth()];
        byte[][] blue   = new byte[source.getHeight()][source.getWidth()];

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                byte luma    = (byte) (source.getLuma()[y][x]     - 16);
                byte chromaU = (byte) (source.getChromaU()[y][x]  - 128);
                byte chromaV = (byte) (source.getChromaV()[y][x]  - 128);
                red[y][x]    = (byte) (1.164D * (double) luma                             + 1.596D * (double) chromaV);
                green[y][x]  = (byte) (1.164D * (double) luma - 0.392D * (double) chromaU - 0.813D * (double) chromaV);
                blue[y][x]   = (byte) (1.164D * (double) luma + 2.017D * (double) chromaU);
            }
        }
        return RGBData.fromArrays(red, green, blue);
    }
}
