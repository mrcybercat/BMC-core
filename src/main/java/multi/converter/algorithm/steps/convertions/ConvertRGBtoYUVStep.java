package multi.converter.algorithm.steps.convertions;

import multi.converter.data.color.RGBData;
import multi.converter.data.color.YUVData;
import multi.converter.algorithm.steps.AlgorithmStep;

public class ConvertRGBtoYUVStep extends AlgorithmStep<YUVData, RGBData> {

    // TODO: Fix value flipping
    @Override
    public YUVData performAlgorithmStep(RGBData source) {
        int[][] luma = new int[source.getHeight()][source.getWidth()];
        int[][] chromaU = new int[source.getHeight()][source.getWidth()];
        int[][] chromaV = new int[source.getHeight()][source.getWidth()];

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int r = source.getRed()[y][x] & 0xFF;   // Convert byte to unsigned int
                int g = source.getGreen()[y][x] & 0xFF;
                int b = source.getBlue()[y][x] & 0xFF;

                int yVal = (int) (0.257 * r + 0.504 * g + 0.098 * b + 16);
                int uVal = (int) (-0.148 * r - 0.291 * g + 0.439 * b + 128);
                int vVal = (int) (0.439 * r - 0.368 * g - 0.071 * b + 128);

                luma[y][x] = Math.max(0, Math.min(255, yVal));
                chromaU[y][x] = Math.max(0, Math.min(255, uVal));
                chromaV[y][x] = Math.max(0, Math.min(255, vVal));
            }
        }

        return YUVData.fromArrays(convertToByteArray(luma), convertToByteArray(chromaU), convertToByteArray(chromaV));
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
