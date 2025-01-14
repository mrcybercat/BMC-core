package multi.converter.algorithm.steps.convertions;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.color.GrayScaleData;
import multi.converter.data.color.RGBData;

public class ConvertRGBToGrayscale extends AlgorithmStep<GrayScaleData, RGBData> {
    @Override
    public GrayScaleData performAlgorithmStep(RGBData source) throws UnableToPerformStepException {
        double[][] grayscale = new double[source.getHeight()][source.getWidth()];
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                grayscale[y][x] = source.getChannelAverageAtXY(x, y);
            }
        }
        return GrayScaleData.fromArrays(grayscale);
    }
}
