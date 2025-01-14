package multi.converter.algorithm.steps.convertions;

import multi.converter.algorithm.steps.AlgorithmStep;
import multi.converter.algorithm.steps.UnableToPerformStepException;
import multi.converter.data.CuboidData;
import multi.converter.data.RGBSequence;
import multi.converter.data.color.RGBData;

import java.util.List;


public class ConvertImageSequenceToCuboidDataStep extends AlgorithmStep<CuboidData, RGBSequence> {
    @Override
    public CuboidData performAlgorithmStep(RGBSequence source) throws UnableToPerformStepException {
        double[][][] data = new double[source.getDuration()][source.getHeight()][source.getWidth()];
        List<RGBData> rgbDataList = source.getRGBData();

        for(int z = 0; z < source.getDuration(); z++){
            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    data[z][y][x]  = rgbDataList.get(z).getChannelAverageAtXY(x, y);
                }
            }
        }

        return new CuboidData(data);
    }
}
