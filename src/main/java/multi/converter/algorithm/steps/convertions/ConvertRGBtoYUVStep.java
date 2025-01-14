package multi.converter.algorithm.steps.convertions;

import multi.converter.data.color.RGBData;
import multi.converter.data.color.YUVData;
import multi.converter.algorithm.steps.AlgorithmStep;

public class ConvertRGBtoYUVStep extends AlgorithmStep<YUVData, RGBData> {

    // TODO: Fix value flipping
    @Override
    public YUVData performAlgorithmStep(RGBData source) {
        byte[][] luma       = new byte[source.getHeight()][source.getWidth()];
        byte[][] chromaU    = new byte[source.getHeight()][source.getWidth()];
        byte[][] chromaV    = new byte[source.getHeight()][source.getWidth()];

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                luma[y][x]    = (byte) ( 0.257D * (double) source.getRed()  [y][x]
                                       + 0.504D * (double) source.getGreen()[y][x]
                                       + 0.098D * (double) source.getBlue() [y][x] + 16);
                chromaU[y][x] = (byte) (-0.148D * (double) source.getRed()  [y][x]
                                       - 0.291D * (double) source.getGreen()[y][x]
                                       + 0.439D * (double) source.getBlue() [y][x] + 128);
                chromaV[y][x] = (byte) ( 0.439D * (double) source.getRed()  [y][x]
                                       - 0.368D * (double) source.getGreen()[y][x]
                                       - 0.071D * (double) source.getBlue() [y][x] + 128);
            }
        }
        return YUVData.fromArrays(luma, chromaU, chromaV);
    }
}
