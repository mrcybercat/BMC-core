package multi.converter.algorithm.steps.convertions;

import multi.converter.data.color.RGBData;
import multi.converter.data.color.YUVData;
import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;

public class ConvertYUVtoRGBStep extends AlgorithmStep<RGBData, YUVData> {

    // TODO: Fix value flipping
    @Override
    public RGBData performAlgorithmStep(YUVData source) {
        if (source.getHeight() != source.getHeightChromaU() || source.getWidth() != source.getWidthChromaV()) {
            throw new UnableToPerformStepException("Cannot perform conversion if U or V channels were down scaled by unspecified factor");
        }

        int[][] red   = new int[source.getHeight()][source.getWidth()];
        int[][] green = new int[source.getHeight()][source.getWidth()];
        int[][] blue  = new int[source.getHeight()][source.getWidth()];

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int luma    = (source.getLuma()[y][x] & 0xFF) - 16;      // Convert byte to unsigned int
                int chromaU = (source.getChromaU()[y][x] & 0xFF) - 128;  // Convert byte to unsigned int
                int chromaV = (source.getChromaV()[y][x] & 0xFF) - 128;

                int r = (int) (1.164 * luma + 1.596 * chromaV);
                int g = (int) (1.164 * luma - 0.392 * chromaU - 0.813 * chromaV);
                int b = (int) (1.164 * luma + 2.017 * chromaU);

                red[y][x]   = Math.max(0, Math.min(255, r));
                green[y][x] = Math.max(0, Math.min(255, g));
                blue[y][x]  = Math.max(0, Math.min(255, b));
            }
        }

        return RGBData.fromArrays(convertToByteArray(red), convertToByteArray(green), convertToByteArray(blue));
    }

    // Helper method to convert int array to byte array
    private byte[][] convertToByteArray(int[][] source) {
        byte[][] result = new byte[source.length][source[0].length];
        for (int y = 0; y < source.length; y++) {
            for (int x = 0; x < source[0].length; x++) {
                result[y][x] = (byte) source[y][x];
            }
        }
        return result;
    }
}
